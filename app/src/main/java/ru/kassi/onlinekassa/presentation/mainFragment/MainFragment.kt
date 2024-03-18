package ru.kassi.onlinekassa.presentation.mainFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.kassi.onlinekassa.R
import ru.kassi.onlinekassa.data.NewsMockData
import ru.kassi.onlinekassa.data.PointMockData
import ru.kassi.onlinekassa.databinding.FragmentMainBinding
import ru.kassi.onlinekassa.presentation.base.BaseFragment
import ru.kassi.onlinekassa.presentation.base.mvi.EmptyNavArgs
import ru.kassi.onlinekassa.presentation.base.viewBinding
import ru.kassi.onlinekassa.presentation.launchActivity.LaunchViewModel
import ru.kassi.onlinekassa.presentation.mainFragment.adapter.NewsAdapter
import ru.kassi.onlinekassa.presentation.mainFragment.adapter.PointsAdapter

@AndroidEntryPoint
class MainFragment : BaseFragment<EmptyNavArgs, MainFragmentState, MainFragmentIntent, MainFragmentViewModel>() {

    private val binding by viewBinding(FragmentMainBinding::bind)

    override val viewModel: MainFragmentViewModel by viewModels()

    private lateinit var pointsAdapter: PointsAdapter
    private lateinit var newsAdapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pointsAdapter = PointsAdapter{
            dispatchIntent(MainFragmentIntent.PointClick(it.toString()))
        }
        newsAdapter = NewsAdapter {
            dispatchIntent(MainFragmentIntent.NewsClick(it))
        }

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarInclude.rightIcon.background = getDrawable(R.drawable.ic_profile)
        binding.toolbarInclude.rightIcon.scaleType = ImageView.ScaleType.CENTER_INSIDE
        binding.toolbarInclude.rightIcon.setOnClickListener {
            viewModel.goToProfile()
        }
        newsAdapter.data = viewModel.getNewsList()
        binding.points.adapter = pointsAdapter
        binding.news.adapter = newsAdapter
        binding.news.isVisible = viewModel.showNews()
        binding.newsTitle.isVisible = viewModel.showNews()
        viewModel.errorToast.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }

    override fun renderState(viewState: MainFragmentState) {
        super.renderState(viewState)
        with(viewState){
            with(binding){
                pointsAdapter.data = pointList.orEmpty()
                userName?.let {
                    toolbarInclude.toolbar.title = getString(R.string.common_greeting_title, it)
                }
                expiredKassa.isVisible = haveExpiredKass
            }
        }
    }

    private fun dispatchIntent(intent: MainFragmentIntent) {
        viewModel.handleIntent(intent)
    }
}
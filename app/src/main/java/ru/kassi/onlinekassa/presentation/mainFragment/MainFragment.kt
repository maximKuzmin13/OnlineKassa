package ru.kassi.onlinekassa.presentation.mainFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.kassi.onlinekassa.R
import ru.kassi.onlinekassa.data.PointMockData
import ru.kassi.onlinekassa.databinding.FragmentMainBinding
import ru.kassi.onlinekassa.presentation.base.BaseFragment
import ru.kassi.onlinekassa.presentation.base.viewBinding
import ru.kassi.onlinekassa.presentation.launchActivity.LaunchViewModel
import ru.kassi.onlinekassa.presentation.mainFragment.adapter.PointsAdapter

@AndroidEntryPoint
class MainFragment : BaseFragment() {

    private val binding by viewBinding(FragmentMainBinding::bind)

    private val viewModel: MainFragmentViewModel by viewModels()

    private lateinit var adapter: PointsAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter = PointsAdapter()
        adapter.data = PointMockData.data
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarInclude.toolbar.title = getString(R.string.common_greeting_title, "Алексей")
        binding.toolbarInclude.rightIcon.background = getDrawable(R.drawable.ic_profile)
        binding.toolbarInclude.rightIcon.scaleType = ImageView.ScaleType.CENTER_INSIDE
        binding.toolbarInclude.rightIcon.setOnClickListener {
            viewModel.goToLogin()
        }
        binding.recycler.adapter = adapter
    }
}
package ru.kassi.onlinekassa.presentation.profileFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.kassi.onlinekassa.R
import ru.kassi.onlinekassa.data.ProfileMockData
import ru.kassi.onlinekassa.databinding.FragmentMainBinding
import ru.kassi.onlinekassa.databinding.FragmentProfileBinding
import ru.kassi.onlinekassa.presentation.base.BaseFragment
import ru.kassi.onlinekassa.presentation.base.viewBinding
import ru.kassi.onlinekassa.presentation.mainFragment.MainFragmentIntent
import ru.kassi.onlinekassa.presentation.mainFragment.MainFragmentViewModel
import ru.kassi.onlinekassa.presentation.profileFragment.adapter.ProfileAdapter

@AndroidEntryPoint
class ProfileFragment : BaseFragment() {

    private val binding by viewBinding(FragmentProfileBinding::bind)

    private val viewModel: ProfileViewModel by viewModels()

    private lateinit var adapter: ProfileAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter = ProfileAdapter()
        adapter.data = ProfileMockData.data
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarInclude.toolbar.title = "Профиль"
        binding.toolbarInclude.toolbar.setNavigationIcon(R.drawable.ic_back)
        binding.toolbarInclude.toolbar.setNavigationOnClickListener {
            dispatchIntent(ProfileIntent.Back)
        }
        binding.recycler.adapter = adapter
    }

    private fun dispatchIntent(intent: ProfileIntent) {
        viewModel.handleIntent(intent)
    }
}
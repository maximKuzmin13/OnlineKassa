package ru.kassi.onlinekassa.presentation.mainFragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import ru.kassi.onlinekassa.databinding.FragmentMainBinding
import ru.kassi.onlinekassa.presentation.base.BaseFragment
import ru.kassi.onlinekassa.presentation.base.viewBinding

class MainFragment : BaseFragment() {

    private val binding by viewBinding(FragmentMainBinding::bind)

    private val mainFragmentViewModel by lazy { ViewModelProviders.of(this).get(MainFragmentViewModel::class.java)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
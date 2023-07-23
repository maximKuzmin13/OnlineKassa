package ru.kassi.onlinekassa.presentation.loginFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import ru.kassi.onlinekassa.R
import ru.kassi.onlinekassa.databinding.FragmentLoginBinding
import ru.kassi.onlinekassa.presentation.base.BaseFragment
import ru.kassi.onlinekassa.presentation.base.viewBinding

class LoginFragment: BaseFragment() {

    private val binding by viewBinding(FragmentLoginBinding::bind)
    val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }
}
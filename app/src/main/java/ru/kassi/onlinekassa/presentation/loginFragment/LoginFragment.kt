package ru.kassi.onlinekassa.presentation.loginFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.kassi.onlinekassa.R
import ru.kassi.onlinekassa.databinding.FragmentLoginBinding
import ru.kassi.onlinekassa.presentation.base.BaseFragment
import ru.kassi.onlinekassa.presentation.base.mvi.EmptyNavArgs
import ru.kassi.onlinekassa.presentation.base.viewBinding
@AndroidEntryPoint
class LoginFragment: BaseFragment<EmptyNavArgs, LoginState, LoginIntent, LoginViewModel>() {

    private val binding by viewBinding(FragmentLoginBinding::bind)

    override val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            register.setText(R.string.register)
            register.onClick {
                dispatchAction(LoginIntent.RegisterClick)
            }
            register.setState(true)
            next.setText(R.string.auth)
            next.setState(true)
            next.onClick {
                dispatchAction(LoginIntent.LoginClick)
            }
        }
    }

    private fun dispatchAction(intent: LoginIntent) {
        viewModel.handleIntent(intent)
    }
}
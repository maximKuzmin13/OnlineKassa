package ru.kassi.onlinekassa.presentation.loginFragment

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.kassi.onlinekassa.R
import ru.kassi.onlinekassa.databinding.FragmentLoginBinding
import ru.kassi.onlinekassa.presentation.base.BaseFragment
import ru.kassi.onlinekassa.presentation.base.viewBinding
@AndroidEntryPoint
class LoginFragment: BaseFragment() {

    private val binding by viewBinding(FragmentLoginBinding::bind)

    private val viewModel: LoginViewModel by viewModels()

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
            inn.setHint(R.string.inn)
            inn.setInputType(InputType.TYPE_CLASS_NUMBER)
            inn.setMaxLength(12)
            login.setHint(R.string.login)
            login.setMaxLength(10)
            password.setHint(R.string.password)
            password.setMaxLength(8)
            password.setImeOptions(EditorInfo.IME_ACTION_DONE)
            register.setText(R.string.register)
            register.onClick {
                dispatchAction(LoginIntent.Click)
            }
            register.enable()
            next.setText(R.string.next)
            next.disable()
        }
    }

    private fun dispatchAction(intent: LoginIntent) {
        viewModel.handleIntent(intent)
    }
}
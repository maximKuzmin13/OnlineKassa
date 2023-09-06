package ru.kassi.onlinekassa.presentation.registrationFragment

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.kassi.onlinekassa.R
import ru.kassi.onlinekassa.databinding.FragmentAuthBinding
import ru.kassi.onlinekassa.databinding.FragmentRegistrationBinding
import ru.kassi.onlinekassa.presentation.authFragment.AuthIntent
import ru.kassi.onlinekassa.presentation.authFragment.AuthViewModel
import ru.kassi.onlinekassa.presentation.base.BaseFragment
import ru.kassi.onlinekassa.presentation.base.mvi.EmptyNavArgs
import ru.kassi.onlinekassa.presentation.base.viewBinding

@AndroidEntryPoint
class RegistrationFragment : BaseFragment<EmptyNavArgs, RegistrationState, RegistrationIntent, RegistrationViewModel>() {

    private val binding by viewBinding(FragmentRegistrationBinding::bind)

    override val viewModel: RegistrationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registration, container, false)
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
            next.setText(R.string.next)
            next.setState(true)
            next.onClick {
                dispatchAction(RegistrationIntent.Next)
            }
        }
    }

    private fun dispatchAction(intent: RegistrationIntent) {
        viewModel.handleIntent(intent)
    }
}
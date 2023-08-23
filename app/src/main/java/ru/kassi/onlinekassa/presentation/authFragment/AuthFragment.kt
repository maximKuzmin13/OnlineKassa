package ru.kassi.onlinekassa.presentation.authFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.kassi.onlinekassa.R
import ru.kassi.onlinekassa.databinding.FragmentAuthBinding
import ru.kassi.onlinekassa.presentation.base.BaseFragment
import ru.kassi.onlinekassa.presentation.base.mvi.EmptyNavArgs
import ru.kassi.onlinekassa.presentation.base.viewBinding

@AndroidEntryPoint
class AuthFragment : BaseFragment<EmptyNavArgs>() {

    private val binding by viewBinding(FragmentAuthBinding::bind)

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            login.setHint(R.string.login)
            login.setMaxLength(10)
            password.setHint(R.string.password)
            password.setMaxLength(8)
            password.setImeOptions(EditorInfo.IME_ACTION_DONE)
            next.setText(R.string.next)
            next.enable()
            next.onClick {
                dispatchAction(AuthIntent.Next)
            }
        }
    }

    private fun dispatchAction(intent: AuthIntent) {
        viewModel.handleIntent(intent)
    }
}
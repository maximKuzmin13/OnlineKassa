package ru.kassi.onlinekassa.presentation.authFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.view.isNotEmpty
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.kassi.onlinekassa.R
import ru.kassi.onlinekassa.databinding.FragmentAuthBinding
import ru.kassi.onlinekassa.extentions.args
import ru.kassi.onlinekassa.extentions.withArgs
import ru.kassi.onlinekassa.presentation.base.BaseFragment
import ru.kassi.onlinekassa.presentation.base.viewBinding

@AndroidEntryPoint
class AuthFragment : BaseFragment<AuthNavArgs, AuthState, AuthIntent, AuthViewModel>() {

    private val binding by viewBinding(FragmentAuthBinding::bind)

    override val viewModel: AuthViewModel by viewModels()

    companion object {
        private const val AUTH_ARGS = "auth_args"
        fun newInstance(navArgs: AuthNavArgs) = AuthFragment().withArgs {
            putParcelable(
                AUTH_ARGS, navArgs
            )
        }
    }

    private val navArgs: AuthNavArgs by args(AUTH_ARGS)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            login.setHint(R.string.login)
            login.setMaxLength(10)
            password.setHint(R.string.password)
            password.setMaxLength(8)
            password.setImeOptions(EditorInfo.IME_ACTION_DONE)
            next.setText(R.string.next)
            next.onClick {
                dispatchAction(AuthIntent.Next)
            }
            viewModel.errorToast.observe(viewLifecycleOwner) {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun renderState(viewState: AuthState) {
        super.renderState(viewState)
        with(viewState) {
            with(binding) {
                dispatchAction(AuthIntent.Inn(navArgs.inn))
                login.editText.doAfterTextChanged {
                    dispatchAction(AuthIntent.Login(it.toString()))
                }
                next.setState(loginS?.isNotBlank() == true && passS?.isNotBlank() == true)
                password.editText.doAfterTextChanged {
                    dispatchAction(AuthIntent.Pass(it.toString()))
                }
                next.setState(passS?.isNotEmpty() == true && loginS?.isNotEmpty() == true)
            }
        }
    }

    private fun dispatchAction(intent: AuthIntent) {
        viewModel.handleIntent(intent)
    }
}
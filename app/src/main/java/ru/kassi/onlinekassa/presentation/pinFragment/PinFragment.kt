package ru.kassi.onlinekassa.presentation.pinFragment

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.kassi.onlinekassa.R
import ru.kassi.onlinekassa.databinding.FragmentMainBinding
import ru.kassi.onlinekassa.databinding.FragmentPinBinding
import ru.kassi.onlinekassa.presentation.base.BaseFragment
import ru.kassi.onlinekassa.presentation.base.mvi.EmptyNavArgs
import ru.kassi.onlinekassa.presentation.base.viewBinding
import ru.kassi.onlinekassa.presentation.loginFragment.LoginViewModel

@AndroidEntryPoint
class PinFragment : BaseFragment<EmptyNavArgs>() {

    private val binding by viewBinding(FragmentPinBinding::bind)

    private val viewModel: PinViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            one.apply {
                onClick { viewModel.addNumber("1") }
                setText("1")
            }
            two.apply {
                onClick { viewModel.addNumber("2") }
                setText("2")
            }
            three.apply {
                onClick { viewModel.addNumber("3") }
                setText("3")
            }
            four.apply {
                onClick { viewModel.addNumber("4") }
                setText("4")
            }
            five.apply {
                onClick { viewModel.addNumber("5") }
                setText("5")
            }
            six.apply {
                onClick { viewModel.addNumber("6") }
                setText("6")
            }
            seven.apply {
                onClick { viewModel.addNumber("7") }
                setText("7")
            }
            eight.apply {
                onClick { viewModel.addNumber("8") }
                setText("8")
            }
            nine.apply {
                onClick { viewModel.addNumber("9") }
                setText("9")
            }
            zero.apply {
                onClick { viewModel.addNumber("0") }
                setText("0")
            }
            fingerprint.setOnClickListener {
                showBiometricPrompt()
            }
            remove.setOnClickListener {
                viewModel.removeNumber()
            }
            viewModel.codeSize.observe(viewLifecycleOwner) {
                when (it) {
                    1 -> {
                        firstPin.setImageDrawable(getDrawable(R.drawable.circle_filled))
                        secondPin.setImageDrawable(getDrawable(R.drawable.circle))
                        thirdPin.setImageDrawable(getDrawable(R.drawable.circle))
                        fourthPin.setImageDrawable(getDrawable(R.drawable.circle))
                    }
                    2 -> {
                        firstPin.setImageDrawable(getDrawable(R.drawable.circle_filled))
                        secondPin.setImageDrawable(getDrawable(R.drawable.circle_filled))
                        thirdPin.setImageDrawable(getDrawable(R.drawable.circle))
                        fourthPin.setImageDrawable(getDrawable(R.drawable.circle))
                    }
                    3 -> {
                        firstPin.setImageDrawable(getDrawable(R.drawable.circle_filled))
                        secondPin.setImageDrawable(getDrawable(R.drawable.circle_filled))
                        thirdPin.setImageDrawable(getDrawable(R.drawable.circle_filled))
                        fourthPin.setImageDrawable(getDrawable(R.drawable.circle))
                    }
                    4 -> {
                        firstPin.setImageDrawable(getDrawable(R.drawable.circle_filled))
                        secondPin.setImageDrawable(getDrawable(R.drawable.circle_filled))
                        thirdPin.setImageDrawable(getDrawable(R.drawable.circle_filled))
                        fourthPin.setImageDrawable(getDrawable(R.drawable.circle_filled))
                    }
                    else -> {
                        firstPin.setImageDrawable(getDrawable(R.drawable.circle))
                        secondPin.setImageDrawable(getDrawable(R.drawable.circle))
                        thirdPin.setImageDrawable(getDrawable(R.drawable.circle))
                        fourthPin.setImageDrawable(getDrawable(R.drawable.circle))
                    }
                }
            }
        }
    }

    private fun showBiometricPrompt() {
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Авторизация через биометрию")
            .setSubtitle("Авторизуйтесь через ваши биометрические данные")
            .setNegativeButtonText("Отмена")
            .build()

        val biometricPrompt =
            BiometricPrompt(this, ContextCompat.getMainExecutor(context),
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                        super.onAuthenticationError(errorCode, errString)
                        showMessage("Ошибка авторизации")
                    }

                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        viewModel.authorised()
                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        showMessage("Ошибка авторизации")
                    }
                })

        biometricPrompt.authenticate(promptInfo)
    }

    private fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
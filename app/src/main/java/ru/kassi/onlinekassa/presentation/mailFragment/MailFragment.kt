package ru.kassi.onlinekassa.presentation.mailFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.kassi.onlinekassa.R
import ru.kassi.onlinekassa.databinding.FragmentLoginBinding
import ru.kassi.onlinekassa.databinding.FragmentMailBinding
import ru.kassi.onlinekassa.presentation.base.BaseFragment
import ru.kassi.onlinekassa.presentation.base.mvi.EmptyNavArgs
import ru.kassi.onlinekassa.presentation.base.viewBinding
import ru.kassi.onlinekassa.presentation.loginFragment.LoginViewModel

@AndroidEntryPoint
class MailFragment: BaseFragment<EmptyNavArgs>() {


    private val binding by viewBinding(FragmentMailBinding::bind)

    private val viewModel: MailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            positive.apply {
                setText("Да")
                onClick {
                    dispatchAction(MailIntent.Positive)
                }
            }
            negative.apply {
                setText("Нет")
                onClick {
                    dispatchAction(MailIntent.Negative)
                }
            }
        }
    }

    fun dispatchAction(intent: MailIntent) {
        viewModel.handleIntent(intent)
    }
}
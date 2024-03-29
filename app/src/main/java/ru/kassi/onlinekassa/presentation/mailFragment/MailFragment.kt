package ru.kassi.onlinekassa.presentation.mailFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.kassi.onlinekassa.R
import ru.kassi.onlinekassa.databinding.FragmentMailBinding
import ru.kassi.onlinekassa.presentation.base.BaseFragment
import ru.kassi.onlinekassa.presentation.base.mvi.EmptyNavArgs
import ru.kassi.onlinekassa.presentation.base.viewBinding

@AndroidEntryPoint
class MailFragment: BaseFragment<EmptyNavArgs, MailState, MailIntent, MailViewModel>() {


    private val binding by viewBinding(FragmentMailBinding::bind)

    override val viewModel: MailViewModel by viewModels()

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

    override fun renderState(viewState: MailState) {
        super.renderState(viewState)
        with(viewState){
            with(binding){
                mail.text = mailData
            }
        }
    }

    fun dispatchAction(intent: MailIntent) {
        viewModel.handleIntent(intent)
    }
}
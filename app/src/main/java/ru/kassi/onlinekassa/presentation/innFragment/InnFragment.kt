package ru.kassi.onlinekassa.presentation.innFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.kassi.onlinekassa.R
import ru.kassi.onlinekassa.databinding.FragmentInnBinding
import ru.kassi.onlinekassa.presentation.base.BaseFragment
import ru.kassi.onlinekassa.presentation.base.mvi.EmptyNavArgs
import ru.kassi.onlinekassa.presentation.base.viewBinding

@AndroidEntryPoint
class InnFragment: BaseFragment<EmptyNavArgs, InnState, InnIntent, InnViewModel>() {

    private val binding by viewBinding(FragmentInnBinding::bind)

    override val viewModel: InnViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_inn, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            inn.setHint(R.string.inn)
            next.setText(R.string.next)
            next.onClick {
                dispatchIntent(InnIntent.Next)
            }
            viewModel.errorToast.observe(viewLifecycleOwner) {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun renderState(viewState: InnState) {
        super.renderState(viewState)
        with(viewState){
            with(binding){
                inn.editText.doAfterTextChanged {
                    dispatchIntent(InnIntent.Inn(it.toString()))
                }
            }
        }
    }

    private fun dispatchIntent(intent: InnIntent) {
        viewModel.handleIntent(intent)
    }
}
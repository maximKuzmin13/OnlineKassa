package ru.kassi.onlinekassa.presentation.pdfFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.kassi.onlinekassa.databinding.FragmentPdfBinding
import ru.kassi.onlinekassa.extentions.args
import ru.kassi.onlinekassa.extentions.withArgs
import ru.kassi.onlinekassa.presentation.base.BaseFragment
import ru.kassi.onlinekassa.presentation.base.mvi.EmptyNavArgs
import ru.kassi.onlinekassa.presentation.base.viewBinding
import ru.kassi.onlinekassa.presentation.kassiFragment.KassaNavArgs
import ru.kassi.onlinekassa.presentation.kassiFragment.KassiFragment

@AndroidEntryPoint
class PdfFragment: BaseFragment<EmptyNavArgs, PdfState, PdfIntent, PdfViewModel>() {

    private val binding by viewBinding(FragmentPdfBinding::bind)

    override val viewModel: PdfViewModel by viewModels()

    companion object {
        private const val PDF_ARGS = "pdf_args"
        fun newInstance(navArgs: PdfNavArgs) = PdfFragment().withArgs {
            putParcelable(
                PDF_ARGS, navArgs
            )
        }
    }

    private val navArgs: PdfNavArgs by args(PDF_ARGS)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(ru.kassi.onlinekassa.R.layout.fragment_pdf, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dispatchIntent(PdfIntent.Num(navArgs.num))
        binding.toolbarInclude.toolbar.title = "Счёт"
        binding.toolbarInclude.toolbar.setNavigationIcon(ru.kassi.onlinekassa.R.drawable.ic_back)
        binding.toolbarInclude.toolbar.setNavigationOnClickListener {
            dispatchIntent(PdfIntent.Back)
        }
        binding.pdfView.fromAsset("pdf_test.pdf").load()
    }

    private fun dispatchIntent(intent: PdfIntent) {
        viewModel.handleIntent(intent)
    }
}
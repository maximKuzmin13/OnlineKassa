package ru.kassi.onlinekassa.presentation.pdfFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.kassi.onlinekassa.databinding.FragmentPdfBinding
import ru.kassi.onlinekassa.presentation.base.BaseFragment
import ru.kassi.onlinekassa.presentation.base.mvi.EmptyNavArgs
import ru.kassi.onlinekassa.presentation.base.viewBinding

@AndroidEntryPoint
class PdfFragment: BaseFragment<EmptyNavArgs>() {

    private val binding by viewBinding(FragmentPdfBinding::bind)

    private val viewModel: PdfViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(ru.kassi.onlinekassa.R.layout.fragment_pdf, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
package ru.kassi.onlinekassa.presentation.kassiFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.kassi.onlinekassa.data.KassiMockData
import ru.kassi.onlinekassa.databinding.FragmentKassiBinding
import ru.kassi.onlinekassa.presentation.base.BaseFragment
import ru.kassi.onlinekassa.presentation.base.mvi.EmptyNavArgs
import ru.kassi.onlinekassa.presentation.base.viewBinding
import ru.kassi.onlinekassa.presentation.kassiFragment.adapter.KassiAdapter


@AndroidEntryPoint
class KassiFragment : BaseFragment<EmptyNavArgs>() {

    private val binding by viewBinding(FragmentKassiBinding::bind)

    private val viewModel: KassiViewModel by viewModels()

    private lateinit var adapter: KassiAdapter

    companion object {
        const val MIME_TYPE_PDF = "application/pdf"
        const val PDF_DOWNLOAD_FAIL = "Не удалось загрузить счет"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter = KassiAdapter {
            dispatchIntent(KassiIntent.LoadPdf)
        }
        adapter.data = KassiMockData.data
        return inflater.inflate(ru.kassi.onlinekassa.R.layout.fragment_kassi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarInclude.toolbar.title = "Сервисы"
        binding.toolbarInclude.toolbar.setNavigationIcon(ru.kassi.onlinekassa.R.drawable.ic_back)
        binding.toolbarInclude.toolbar.setNavigationOnClickListener {
            dispatchIntent(KassiIntent.Back)
        }
        binding.recycler.adapter = adapter
    }

    private fun dispatchIntent(intent: KassiIntent) {
        viewModel.handleIntent(intent)
    }
}
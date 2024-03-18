package ru.kassi.onlinekassa.presentation.kassiFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.kassi.onlinekassa.R
import ru.kassi.onlinekassa.databinding.FragmentKassiBinding
import ru.kassi.onlinekassa.extentions.args
import ru.kassi.onlinekassa.extentions.withArgs
import ru.kassi.onlinekassa.presentation.base.BaseFragment
import ru.kassi.onlinekassa.presentation.base.viewBinding
import ru.kassi.onlinekassa.presentation.kassiFragment.adapter.KassiAdapter
import ru.kassi.onlinekassa.presentation.kassiFragment.adapter.SpacesItemDecoration


@AndroidEntryPoint
class KassiFragment : BaseFragment<KassaNavArgs, KassiState, KassiIntent, KassiViewModel>() {

    private val binding by viewBinding(FragmentKassiBinding::bind)

    override val viewModel: KassiViewModel by viewModels()

    private lateinit var adapter: KassiAdapter

    companion object {
        private const val KASSA_ARGS = "kassa_args"
        fun newInstance(navArgs: KassaNavArgs) = KassiFragment().withArgs {
            putParcelable(
                KASSA_ARGS, navArgs
            )
        }
    }

    private val navArgs: KassaNavArgs by args(KASSA_ARGS)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter = KassiAdapter {
            dispatchIntent(KassiIntent.LoadPdf(it))
        }
        return inflater.inflate(R.layout.fragment_kassi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dispatchIntent(KassiIntent.Num(navArgs.num))
        binding.toolbarInclude.toolbar.title = "Сервисы"
        binding.toolbarInclude.toolbar.setNavigationIcon(R.drawable.ic_back)
        binding.toolbarInclude.toolbar.setNavigationOnClickListener {
            dispatchIntent(KassiIntent.Back)
        }
        binding.recycler.adapter = adapter
        viewModel.errorToast.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing)
        val spacesItemDecoration = SpacesItemDecoration(spacingInPixels)
        binding.recycler.addItemDecoration(spacesItemDecoration)
    }

    override fun renderState(viewState: KassiState) {
        super.renderState(viewState)
        with(viewState){
            with(binding){
                adapter.data = kassaList
            }
        }
    }

    private fun dispatchIntent(intent: KassiIntent) {
        viewModel.handleIntent(intent)
    }
}
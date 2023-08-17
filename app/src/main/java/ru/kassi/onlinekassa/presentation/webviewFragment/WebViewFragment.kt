package ru.kassi.onlinekassa.presentation.webviewFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.kassi.onlinekassa.R
import ru.kassi.onlinekassa.databinding.FragmentWebviewBinding
import ru.kassi.onlinekassa.extentions.args
import ru.kassi.onlinekassa.extentions.withArgs
import ru.kassi.onlinekassa.presentation.base.BaseFragment
import ru.kassi.onlinekassa.presentation.base.viewBinding

@AndroidEntryPoint
class WebViewFragment : BaseFragment<WebviewNavArgs>() {

    companion object {
        private const val WEBVIEW_ARGS = "webview_args"

        fun newInstance(navArgs: WebviewNavArgs) =
            WebViewFragment().withArgs { putParcelable(WEBVIEW_ARGS, navArgs) }

    }

    private val binding by viewBinding(FragmentWebviewBinding::bind)

    private val navArgs: WebviewNavArgs by args(WEBVIEW_ARGS)


    private val viewModel: WebviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_webview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarInclude.toolbar.setNavigationIcon(R.drawable.ic_back)
        binding.toolbarInclude.toolbar.setNavigationOnClickListener {
            dispatchIntent(WebviewIntent.Back)
        }
        binding.web.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url != null) {
                    view?.loadUrl(url)
                }
                return true
            }
        }
        binding.web.loadUrl(navArgs.link)
    }

    private fun dispatchIntent(intent: WebviewIntent) {
        viewModel.handleIntent(intent)
    }
}
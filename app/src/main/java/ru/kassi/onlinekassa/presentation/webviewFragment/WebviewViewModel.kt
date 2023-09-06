package ru.kassi.onlinekassa.presentation.webviewFragment

import androidx.lifecycle.SavedStateHandle
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.kassi.onlinekassa.presentation.base.mvi.MviViewModel
import javax.inject.Inject

@HiltViewModel
class WebviewViewModel @Inject constructor(
    private val coordinator: WebviewCoordinator
) :
    MviViewModel<WebviewNavArgs, WebViewState, WebviewIntent>(WebViewState()) {

    override val onError: suspend (Throwable) -> Unit = {}

    override suspend fun reduceState(intent: WebviewIntent) {
        return when (intent) {
            WebviewIntent.Back -> {
                coordinator.back()
            }
        }
    }
}
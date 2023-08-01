package ru.kassi.onlinekassa.presentation.pdfFragment

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import ru.kassi.onlinekassa.data.ResourceManager
import ru.kassi.onlinekassa.di.IoDispatcher
import ru.kassi.onlinekassa.presentation.base.mvi.MviViewModel
import ru.kassi.onlinekassa.presentation.pdfFragment.coordinator.PdfCoordinator
import javax.inject.Inject

@HiltViewModel
class PdfViewModel @Inject constructor(
    private val coordinator: PdfCoordinator,
    private val resources: ResourceManager,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : MviViewModel<PdfState, PdfIntent>(PdfState()) {
    override val onError: suspend (Throwable) -> Unit = {}

    override suspend fun reduceState(intent: PdfIntent): PdfState {
        return when (intent) {
            PdfIntent.Loading -> currentState
            PdfIntent.Start -> currentState
            PdfIntent.Back -> {
                coordinator.backToKassi()
                currentState
            }
        }
    }
}
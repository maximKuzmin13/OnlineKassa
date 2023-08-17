package ru.kassi.onlinekassa.presentation.kassiFragment

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import ru.kassi.onlinekassa.data.ResourceManager
import ru.kassi.onlinekassa.di.IoDispatcher
import ru.kassi.onlinekassa.presentation.base.mvi.MviViewModel
import ru.kassi.onlinekassa.presentation.kassiFragment.coordinator.KassiCoordinator
import javax.inject.Inject

@HiltViewModel
class KassiViewModel @Inject constructor(
    private val kassiCoordinator: KassiCoordinator,
    private val resources: ResourceManager,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : MviViewModel<KassiState, KassiIntent>(KassiState()) {
    override val onError: suspend (Throwable) -> Unit = {}


    companion object {
        const val FILE_PATH = "app/src/main/res/raw/pdf_test.pdf"
    }

    override suspend fun reduceState(intent: KassiIntent): KassiState {
        return when (intent) {
            KassiIntent.Loading -> currentState
            KassiIntent.Start -> currentState
            KassiIntent.Back -> {
                kassiCoordinator.goToMain()
                currentState
            }
            KassiIntent.LoadPdf -> {
                loadPdf()
                currentState
            }
        }
    }

    fun loadPdf() {
        viewModelScope.launch {
            kassiCoordinator.goToPdf()
        }
    }
}
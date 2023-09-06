package ru.kassi.onlinekassa.presentation.kassiFragment

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import ru.kassi.onlinekassa.data.ResourceManager
import ru.kassi.onlinekassa.di.IoDispatcher
import ru.kassi.onlinekassa.presentation.base.mvi.EmptyNavArgs
import ru.kassi.onlinekassa.presentation.base.mvi.MviViewModel
import ru.kassi.onlinekassa.presentation.kassiFragment.coordinator.KassiCoordinator
import javax.inject.Inject

@HiltViewModel
class KassiViewModel @Inject constructor(
    private val kassiCoordinator: KassiCoordinator,
    private val resources: ResourceManager,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : MviViewModel<EmptyNavArgs, KassiState, KassiIntent>(KassiState()) {
    override val onError: suspend (Throwable) -> Unit = {}


    companion object {
        const val FILE_PATH = "app/src/main/res/raw/pdf_test.pdf"
    }

    override suspend fun reduceState(intent: KassiIntent) {
        return when (intent) {
            KassiIntent.Loading -> {}
            KassiIntent.Start -> {}
            KassiIntent.Back -> {
                kassiCoordinator.goToMain()
            }
            KassiIntent.LoadPdf -> {
                loadPdf()
            }
        }
    }

    fun loadPdf() {
        viewModelScope.launch {
            kassiCoordinator.goToPdf()
        }
    }
}
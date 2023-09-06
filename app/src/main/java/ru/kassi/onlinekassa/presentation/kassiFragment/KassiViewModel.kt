package ru.kassi.onlinekassa.presentation.kassiFragment

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import ru.kassi.onlinekassa.data.ResourceManager
import ru.kassi.onlinekassa.di.IoDispatcher
import ru.kassi.onlinekassa.domain.api.kassa.KassaRepository
import ru.kassi.onlinekassa.presentation.base.mvi.EmptyNavArgs
import ru.kassi.onlinekassa.presentation.base.mvi.MviViewModel
import ru.kassi.onlinekassa.presentation.kassiFragment.coordinator.KassiCoordinator
import javax.inject.Inject

@HiltViewModel
class KassiViewModel @Inject constructor(
    private val kassiCoordinator: KassiCoordinator,
    private val kassaRepository: KassaRepository,
    private val resources: ResourceManager,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : MviViewModel<KassaNavArgs, KassiState, KassiIntent>(KassiState()) {
    override val onError: suspend (Throwable) -> Unit = {}

    init {
        loadKassaInfo()
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
            is KassiIntent.Num -> _state.value = currentState.copy(num = intent.num)
        }
    }

    fun loadKassaInfo() {
        viewModelScope.launch {
            val a = kassaRepository.getKassaInfo(currentState.num.orEmpty()).response
            _state.value = currentState.copy(
                kassaList = a
            )
        }
    }

    fun loadPdf() {
        viewModelScope.launch {
            kassiCoordinator.goToPdf()
        }
    }
}
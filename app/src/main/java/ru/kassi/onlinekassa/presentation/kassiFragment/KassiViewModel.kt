package ru.kassi.onlinekassa.presentation.kassiFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.kassi.onlinekassa.data.ModelData
import ru.kassi.onlinekassa.data.ResourceManager
import ru.kassi.onlinekassa.di.IoDispatcher
import ru.kassi.onlinekassa.domain.LoadKassaInfoUseCase
import ru.kassi.onlinekassa.domain.api.kassa.KassaData
import ru.kassi.onlinekassa.domain.api.kassa.KassaRepository
import ru.kassi.onlinekassa.presentation.base.mvi.EmptyNavArgs
import ru.kassi.onlinekassa.presentation.base.mvi.MviViewModel
import ru.kassi.onlinekassa.presentation.kassiFragment.coordinator.KassiCoordinator
import javax.inject.Inject

@HiltViewModel
class KassiViewModel @Inject constructor(
    private val kassiCoordinator: KassiCoordinator,
    private val loadKassaInfoUseCase: LoadKassaInfoUseCase,
    private val resources: ResourceManager,
    @IoDispatcher val dispatcher: CoroutineDispatcher,
) : MviViewModel<KassaNavArgs, KassiState, KassiIntent>(KassiState()) {
    override val onError: suspend (Throwable) -> Unit = {}

    private val handler = CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

    val _errorToast: MutableLiveData<String> = MutableLiveData()
    val errorToast: LiveData<String> = _errorToast

    override suspend fun reduceState(intent: KassiIntent) {
        return when (intent) {
            KassiIntent.Loading -> {}
            KassiIntent.Start -> {}
            KassiIntent.Back -> {
                kassiCoordinator.goToMain()
            }
            is KassiIntent.LoadPdf -> {
                loadPdf(intent.modelData)
            }
            is KassiIntent.Num -> {
                _state.value = currentState.copy(num = intent.num)
                loadKassaInfo()
            }
        }
    }

    fun loadKassaInfo() {
        viewModelScope.launch(handler + dispatcher) {
            val list = loadKassaInfoUseCase.loadKassaInfo(currentState.num.orEmpty())
            _state.value = currentState.copy(
                kassaList = list
            )
        }
    }

    fun handleError(e: Throwable) {
        _errorToast.value = e.message
    }

    fun loadPdf(modelData: ModelData?) {
        viewModelScope.launch(handler) {
            kassiCoordinator.goToPdf(modelData?.model.orEmpty(), modelData?.type, currentState.num ?: "0")
        }
    }
}
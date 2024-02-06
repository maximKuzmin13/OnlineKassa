package ru.kassi.onlinekassa.presentation.innFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.kassi.onlinekassa.domain.api.innCheck.InnRepository
import ru.kassi.onlinekassa.presentation.base.mvi.EmptyNavArgs
import ru.kassi.onlinekassa.presentation.base.mvi.MviViewModel
import javax.inject.Inject

@HiltViewModel
class InnViewModel @Inject constructor(
    private val innRepository: InnRepository,
    private val innCoordinator: InnCoordinator
) : MviViewModel<EmptyNavArgs, InnState, InnIntent>(
    InnState()
) {
    override val onError: suspend (Throwable) -> Unit = {}

    val _errorToast: MutableLiveData<String> = MutableLiveData()
    val errorToast: LiveData<String> = _errorToast

    private val handler = CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

    override suspend fun reduceState(intent: InnIntent) {
        when (intent) {
            InnIntent.Back -> {}
            InnIntent.Loading -> {}
            is InnIntent.Inn -> _state.value = currentState.copy(innS = intent.innS)
            InnIntent.Next -> checkInn()
        }
    }

    private fun checkInn() {
        viewModelScope.launch(handler) {
            val inn = innRepository.checkInn(currentState.innS.orEmpty())
            if (inn.response.inn == 1) {
                innCoordinator.goToAuth(currentState.innS.orEmpty())
            } else {
                innCoordinator.goToStart()
                _errorToast.value = "ИНН не найден"
            }
        }
    }

    private fun handleError(e: Throwable) {
        _errorToast.value = e.message
    }
}
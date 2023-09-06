package ru.kassi.onlinekassa.presentation.registrationFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.kassi.onlinekassa.domain.api.auth.AuthRepository
import ru.kassi.onlinekassa.presentation.base.mvi.EmptyNavArgs
import ru.kassi.onlinekassa.presentation.base.mvi.MviViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val coordinator: RegistrationCoordinator,
    private val authRepository: AuthRepository,
) :
    MviViewModel<EmptyNavArgs, RegistrationState, RegistrationIntent>(RegistrationState()) {
    override val onError: suspend (Throwable) -> Unit = {}

    val _errorToast: MutableLiveData<String> = MutableLiveData()
    val errorToast: LiveData<String> = _errorToast

    private val handler = CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

    override suspend fun reduceState(intent: RegistrationIntent) {
        return when (intent) {
            RegistrationIntent.Loading -> {}
            RegistrationIntent.Start -> {}
            RegistrationIntent.Next -> register()
            is RegistrationIntent.Inn -> _state.value = currentState.copy(innS = intent.inn)
            is RegistrationIntent.Login -> _state.value = currentState.copy(loginS = intent.login)
            is RegistrationIntent.Pass -> _state.value = currentState.copy(passS = intent.pass)
        }
    }

    private fun register() {
        viewModelScope.launch(handler) {
            authRepository.register(
                login = currentState.loginS.orEmpty(),
                inn = currentState.innS.orEmpty(),
                pass = currentState.passS.orEmpty()
            )
            coordinator.goToPin()
        }
    }

    private fun handleError(e: Throwable) {
        _errorToast.value = e.message
    }
}
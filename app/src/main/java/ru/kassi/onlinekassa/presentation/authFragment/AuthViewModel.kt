package ru.kassi.onlinekassa.presentation.authFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.kassi.onlinekassa.domain.api.auth.AuthRepository
import ru.kassi.onlinekassa.presentation.base.mvi.MviViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val coordinator: AuthCoordinator,
    private val authRepository: AuthRepository,
) : MviViewModel<AuthNavArgs, AuthState, AuthIntent>(AuthState()) {
    override val onError: suspend (Throwable) -> Unit = {}

    val _errorToast: MutableLiveData<String> = MutableLiveData()
    val errorToast: LiveData<String> = _errorToast

    private val handler = CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

    override suspend fun reduceState(intent: AuthIntent) {
        return when(intent){
            AuthIntent.Loading -> {}
            AuthIntent.Start -> {}
            AuthIntent.Next -> {
                authorise()
            }
            is AuthIntent.Login -> _state.value = currentState.copy(loginS = intent.login)
            is AuthIntent.Pass -> _state.value = currentState.copy(passS = intent.pass)
            is AuthIntent.Inn -> _state.value = currentState.copy(innS = intent.inn)
        }
    }

    private fun authorise() {
        viewModelScope.launch(handler) {
            authRepository.auth(
                login = currentState.loginS.orEmpty(),
                inn = currentState.innS.orEmpty(),
                pass = currentState.passS.orEmpty()
            )
            coordinator.goToMail()
        }
    }

    private fun handleError(e: Throwable) {
        _errorToast.value = e.message
    }
}
package ru.kassi.onlinekassa.presentation.authFragment

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.kassi.onlinekassa.presentation.base.mvi.MviViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val coordinator: AuthCoordinator
) : MviViewModel<AuthState, AuthIntent>(AuthState()) {
    override val onError: suspend (Throwable) -> Unit = {}

    override suspend fun reduceState(intent: AuthIntent): AuthState {
        return when(intent){
            AuthIntent.Loading -> currentState
            AuthIntent.Start -> currentState
            AuthIntent.Next -> {
                coordinator.goToMail()
                currentState
            }
        }
    }
}
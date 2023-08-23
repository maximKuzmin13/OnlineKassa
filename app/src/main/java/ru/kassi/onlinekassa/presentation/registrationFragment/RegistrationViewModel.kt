package ru.kassi.onlinekassa.presentation.registrationFragment

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.kassi.onlinekassa.presentation.authFragment.AuthCoordinator
import ru.kassi.onlinekassa.presentation.base.mvi.MviViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val coordinator: RegistrationCoordinator
) :
    MviViewModel<RegistrationState, RegistrationIntent>(RegistrationState()) {
    override val onError: suspend (Throwable) -> Unit = {}

    override suspend fun reduceState(intent: RegistrationIntent): RegistrationState {
        return when (intent) {
            RegistrationIntent.Loading -> currentState
            RegistrationIntent.Start -> currentState
            RegistrationIntent.Next -> {
                coordinator.goToPin()
                currentState
            }
        }
    }
}
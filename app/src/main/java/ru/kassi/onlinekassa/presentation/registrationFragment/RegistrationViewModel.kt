package ru.kassi.onlinekassa.presentation.registrationFragment

import androidx.lifecycle.SavedStateHandle
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.kassi.onlinekassa.domain.AuthRepository
import ru.kassi.onlinekassa.presentation.authFragment.AuthCoordinator
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

    override suspend fun reduceState(intent: RegistrationIntent){
        return when (intent) {
            RegistrationIntent.Loading -> {}
            RegistrationIntent.Start -> {}
            RegistrationIntent.Next -> {
                coordinator.goToPin()
            }
        }
    }
}
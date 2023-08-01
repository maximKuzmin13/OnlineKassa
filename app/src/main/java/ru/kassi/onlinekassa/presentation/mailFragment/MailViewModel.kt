package ru.kassi.onlinekassa.presentation.mailFragment

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import ru.kassi.onlinekassa.data.ResourceManager
import ru.kassi.onlinekassa.di.IoDispatcher
import ru.kassi.onlinekassa.presentation.base.mvi.MviViewModel
import ru.kassi.onlinekassa.presentation.mailFragment.coordinator.MailCoordinator
import javax.inject.Inject

@HiltViewModel
class MailViewModel @Inject constructor(
    private val coordinator: MailCoordinator,
    private val resources: ResourceManager,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : MviViewModel<MailState, MailIntent>(MailState()) {
    override val onError: suspend (Throwable) -> Unit = {}

    override suspend fun reduceState(intent: MailIntent): MailState {
        return when (intent) {
            MailIntent.Loading -> currentState
            MailIntent.Start -> currentState
            MailIntent.Negative -> {
                coordinator.goToAuth()
                currentState
            }
            MailIntent.Positive -> {
                coordinator.goToPin()
                currentState
            }
        }
    }
}
package ru.kassi.onlinekassa.presentation.mailFragment

import androidx.lifecycle.SavedStateHandle
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import ru.kassi.onlinekassa.data.ResourceManager
import ru.kassi.onlinekassa.di.IoDispatcher
import ru.kassi.onlinekassa.presentation.base.mvi.EmptyNavArgs
import ru.kassi.onlinekassa.presentation.base.mvi.MviViewModel
import ru.kassi.onlinekassa.presentation.mailFragment.coordinator.MailCoordinator
import javax.inject.Inject

@HiltViewModel
class MailViewModel @Inject constructor(
    private val coordinator: MailCoordinator,
    private val resources: ResourceManager,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : MviViewModel<EmptyNavArgs, MailState, MailIntent>(MailState()) {
    override val onError: suspend (Throwable) -> Unit = {}

    override suspend fun reduceState(intent: MailIntent) {
        return when (intent) {
            MailIntent.Loading -> {}
            MailIntent.Start -> {}
            MailIntent.Negative -> {
                coordinator.goToAuth()
            }
            MailIntent.Positive -> {
                coordinator.goToPin()
            }
        }
    }
}
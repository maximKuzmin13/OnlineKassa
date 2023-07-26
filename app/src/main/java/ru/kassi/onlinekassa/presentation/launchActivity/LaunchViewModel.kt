package ru.kassi.onlinekassa.presentation.launchActivity

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.updateAndGet
import ru.kassi.onlinekassa.di.IoDispatcher
import ru.kassi.onlinekassa.presentation.base.mvi.MviViewModel
import ru.kassi.onlinekassa.presentation.singleActivity.SingleActivity
import javax.inject.Inject

@HiltViewModel
class LaunchViewModel @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : MviViewModel<LaunchState, LaunchIntent>(LaunchState(), dispatcher) {

    override val onError: suspend (Throwable) -> Unit = {
        handleIntent(LaunchIntent.Interrupt)
    }

    override suspend fun reduceState(intent: LaunchIntent): LaunchState {
        return when (intent) {
            LaunchIntent.Interrupt -> {
                _state.updateAndGet { it.copy(isInterrupted = true, isLoading = false) }
            }
            LaunchIntent.FetchData -> {
                // funny: if there's no delay splash is barely seen
                delay(2000L)
                _state.updateAndGet { it.copy(isLoading = false, nextDestination = SingleActivity::class.java) }
            }
        }
    }
}
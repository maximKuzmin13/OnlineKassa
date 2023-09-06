package ru.kassi.onlinekassa.presentation.launchActivity

import androidx.lifecycle.SavedStateHandle
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.updateAndGet
import ru.kassi.onlinekassa.di.IoDispatcher
import ru.kassi.onlinekassa.presentation.base.mvi.EmptyNavArgs
import ru.kassi.onlinekassa.presentation.base.mvi.MviViewModel
import ru.kassi.onlinekassa.presentation.singleActivity.SingleActivity
import javax.inject.Inject

@HiltViewModel
class LaunchViewModel @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : MviViewModel<EmptyNavArgs, LaunchState, LaunchIntent>(LaunchState(), dispatcher = dispatcher) {

    override val onError: suspend (Throwable) -> Unit = {
        handleIntent(LaunchIntent.Interrupt)
    }

    override suspend fun reduceState(intent: LaunchIntent) {
        return when (intent) {
            LaunchIntent.Interrupt -> {
                _state.updateAndGet { it.copy(isInterrupted = true, isLoading = false) }
                Unit
            }
            LaunchIntent.FetchData -> {
                delay(2000L)
                _state.updateAndGet { it.copy(isLoading = false, nextDestination = SingleActivity::class.java) }
                Unit
            }
        }
    }
}
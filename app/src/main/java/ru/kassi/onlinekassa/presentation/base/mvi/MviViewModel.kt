package ru.kassi.onlinekassa.presentation.base.mvi

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class MviViewModel<NavArgs : Parcelable, State : MviState, Intent : MviIntent>(
    initialState: State,
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {

    val errorHandler = CoroutineExceptionHandler { _, throwable ->
        coroutineScope.launch {
            onError(throwable)
        }
    }

    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + dispatcher + errorHandler)
    private val jobLauncher = DefaultJobLauncher(coroutineScope)

    abstract val onError: suspend (Throwable) -> Unit

    protected abstract suspend fun reduceState(intent: Intent)

    protected val _state = MutableStateFlow(initialState)
    val state get() = _state as StateFlow<State>
    val currentState: State get() = state.value

    protected fun safeLaunch(block: suspend () -> Unit): Unit = jobLauncher.addToQueue { block() }

    fun handleIntent(intent: Intent) {
        jobLauncher.addToQueue {
            reduceState(intent)
                .run { _state.update { currentState } }
        }
    }

    override fun onCleared() {
        super.onCleared()
        jobLauncher.exit()
    }
}
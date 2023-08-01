package ru.kassi.onlinekassa.presentation.pinFragment

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import ru.kassi.onlinekassa.data.ResourceManager
import ru.kassi.onlinekassa.di.IoDispatcher
import ru.kassi.onlinekassa.presentation.base.mvi.MviViewModel
import ru.kassi.onlinekassa.presentation.pinFragment.coordinator.PinCoordinator
import javax.inject.Inject

@HiltViewModel
class PinViewModel @Inject constructor(
    private val coordinator: PinCoordinator,
    private val resources: ResourceManager,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : MviViewModel<PinState, PinIntent>(PinState()) {
    override val onError: suspend (Throwable) -> Unit = {}

    val code = mutableListOf<String>()

    override suspend fun reduceState(intent: PinIntent): PinState {
        return when (intent) {
            PinIntent.Loading -> currentState
            PinIntent.Start -> currentState
        }
    }

    fun addNumber(number: String) {
        if (code.size == 3) {
            authorised()
        } else {
            code.add(number)
        }
    }

    fun removeNumber() {
        code.removeLast()
    }

    fun authorised() {
        coordinator.goToMain()
    }
}
package ru.kassi.onlinekassa.presentation.pinFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    val _codeSize: MutableLiveData<Int> = MutableLiveData()
    val codeSize: LiveData<Int> = _codeSize

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
        _codeSize.postValue(code.size)
    }

    fun removeNumber() {
        if (code.size != 0) {
            code.removeLast()
        }
        _codeSize.postValue(code.size)
    }

    fun authorised() {
        coordinator.goToMain()
    }
}
package ru.kassi.onlinekassa.presentation.pinFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.kassi.onlinekassa.data.ResourceManager
import ru.kassi.onlinekassa.di.IoDispatcher
import ru.kassi.onlinekassa.domain.FetchRemoteConfigUseCase
import ru.kassi.onlinekassa.presentation.base.mvi.EmptyNavArgs
import ru.kassi.onlinekassa.presentation.base.mvi.MviViewModel
import ru.kassi.onlinekassa.presentation.pinFragment.coordinator.PinCoordinator
import javax.inject.Inject

@HiltViewModel
class PinViewModel @Inject constructor(
    private val coordinator: PinCoordinator,
    private val resources: ResourceManager,
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val remoteConfigUseCase: FetchRemoteConfigUseCase
) : MviViewModel<EmptyNavArgs, PinState, PinIntent>(PinState()) {
    override val onError: suspend (Throwable) -> Unit = {}

    val code = mutableListOf<String>()

    val _codeSize: MutableLiveData<Int> = MutableLiveData()
    val codeSize: LiveData<Int> = _codeSize

    init {
        viewModelScope.launch {
            remoteConfigUseCase.reload()
        }
    }

    override suspend fun reduceState(intent: PinIntent) {
        return when (intent) {
            PinIntent.Loading -> {}
            PinIntent.Start -> {}
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
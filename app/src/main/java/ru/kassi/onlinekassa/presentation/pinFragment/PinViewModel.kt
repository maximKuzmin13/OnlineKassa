package ru.kassi.onlinekassa.presentation.pinFragment

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.kassi.onlinekassa.data.ResourceManager
import ru.kassi.onlinekassa.di.FirstRunQualifier
import ru.kassi.onlinekassa.di.IoDispatcher
import ru.kassi.onlinekassa.di.UserDataQualifier
import ru.kassi.onlinekassa.domain.FetchRemoteConfigUseCase
import ru.kassi.onlinekassa.presentation.base.mvi.EmptyNavArgs
import ru.kassi.onlinekassa.presentation.base.mvi.MviViewModel
import ru.kassi.onlinekassa.presentation.pinFragment.coordinator.PinCoordinator
import java.lang.Exception
import java.lang.StringBuilder
import javax.inject.Inject

@HiltViewModel
class PinViewModel @Inject constructor(
    private val coordinator: PinCoordinator,
    private val resources: ResourceManager,
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val remoteConfigUseCase: FetchRemoteConfigUseCase,
    @UserDataQualifier private val prefs: SharedPreferences,
    @FirstRunQualifier private val firstRunPrefs: SharedPreferences
) : MviViewModel<EmptyNavArgs, PinState, PinIntent>(PinState()) {
    override val onError: suspend (Throwable) -> Unit = {}

    val code = mutableListOf<String>()

    val _codeSize: MutableLiveData<Int> = MutableLiveData()
    val codeSize: LiveData<Int> = _codeSize
    val _pinError: MutableLiveData<String> = MutableLiveData()
    val pinError: LiveData<String> = _pinError
    private val handler = CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

    init {
        viewModelScope.launch(handler) {
            handleAuth()
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
            code.add(number)
            if (prefs.getString("pin", null) == null) {
                val pin = code.joinToString().replace(", ".toRegex(), "")
                prefs.edit().putString("pin", pin).apply()
            }
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

    fun handleAuth() {
        val token = prefs.getString("tnx", null)
        if (token.isNullOrEmpty()) coordinator.goToLogin()
    }

    fun handleError(throwable: Throwable) {
        print(throwable)
    }

    fun authorised() {
        val pin = prefs.getString("pin", null)
        val currentPin = code.joinToString().replace(", ".toRegex(), "")
        if (currentPin == pin) {
            coordinator.goToMain()
        } else {
            code.clear()
            _pinError.postValue("Пин код не верный")
        }

    }
}
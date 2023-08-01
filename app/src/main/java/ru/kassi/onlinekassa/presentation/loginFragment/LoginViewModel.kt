package ru.kassi.onlinekassa.presentation.loginFragment

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import ru.kassi.onlinekassa.data.ResourceManager
import ru.kassi.onlinekassa.di.IoDispatcher
import ru.kassi.onlinekassa.presentation.pinFragment.coordinator.PinCoordinator
import ru.kassi.onlinekassa.presentation.base.mvi.MviViewModel
import ru.kassi.onlinekassa.presentation.loginFragment.coordinator.LoginFragmentCoordinator
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val coordinator: LoginFragmentCoordinator,
    private val resources: ResourceManager,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : MviViewModel<LoginState, LoginIntent>(LoginState()) {

    init {
        Log.d("tag", "login")
    }

    fun goToMain() {
        coordinator.goToPin()
    }

    override val onError: suspend (Throwable) -> Unit = {}

    override suspend fun reduceState(intent: LoginIntent): LoginState {
        return when (intent) {
            LoginIntent.Loading -> currentState
            LoginIntent.Start -> currentState
            LoginIntent.Click -> {
                coordinator.goToMail()
                currentState
            }
        }
    }

}

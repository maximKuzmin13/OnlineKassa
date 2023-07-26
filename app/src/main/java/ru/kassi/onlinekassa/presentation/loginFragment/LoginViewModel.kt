package ru.kassi.onlinekassa.presentation.loginFragment

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import ru.kassi.onlinekassa.data.ResourceManager
import ru.kassi.onlinekassa.di.IoDispatcher
import ru.kassi.onlinekassa.presentation.loginFragment.coordinator.LoginFragmentCoordinator
import ru.kassi.onlinekassa.presentation.mainFragment.MainFragmentIntent
import ru.kassi.onlinekassa.presentation.mainFragment.MainFragmentState
import ru.kassi.onlinekassa.presentation.mainFragment.coordinator.MainFragmentCoordinator
import ru.kassi.onlinekassa.presentation.base.mvi.MviViewModel
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
        coordinator.goToMain()
    }

    override val onError: suspend (Throwable) -> Unit = {}

    override suspend fun reduceState(intent: LoginIntent): LoginState {
        return when (intent) {
            LoginIntent.Loading -> currentState
            LoginIntent.Start -> currentState
        }
    }

}

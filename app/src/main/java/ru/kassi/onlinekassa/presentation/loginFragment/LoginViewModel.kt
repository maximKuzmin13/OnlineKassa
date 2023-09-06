package ru.kassi.onlinekassa.presentation.loginFragment

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import ru.kassi.onlinekassa.data.ResourceManager
import ru.kassi.onlinekassa.di.IoDispatcher
import ru.kassi.onlinekassa.domain.AuthRepository
import ru.kassi.onlinekassa.presentation.base.mvi.EmptyNavArgs
import ru.kassi.onlinekassa.presentation.base.mvi.MviViewModel
import ru.kassi.onlinekassa.presentation.loginFragment.coordinator.LoginFragmentCoordinator
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val coordinator: LoginFragmentCoordinator,
    private val resources: ResourceManager,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : MviViewModel<EmptyNavArgs, LoginState, LoginIntent>(LoginState()) {

    init {
        Log.d("tag", "login")
    }

    fun goToMain() {
        coordinator.goToPin()
    }

    override val onError: suspend (Throwable) -> Unit = {}

    override suspend fun reduceState(intent: LoginIntent) {
        return when (intent) {
            LoginIntent.Loading -> {}
            LoginIntent.Start -> {}
            LoginIntent.RegisterClick -> {
                coordinator.goToRegister()
            }

            LoginIntent.LoginClick -> {
                coordinator.goToAuth()
            }
        }
    }

}

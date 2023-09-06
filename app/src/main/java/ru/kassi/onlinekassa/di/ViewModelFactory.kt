package ru.kassi.onlinekassa.di

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.Assisted
import kotlinx.coroutines.CoroutineDispatcher
import ru.kassi.onlinekassa.data.ResourceManager
import ru.kassi.onlinekassa.domain.FetchRemoteConfigUseCase
import ru.kassi.onlinekassa.domain.AuthRepository
import ru.kassi.onlinekassa.presentation.loginFragment.LoginViewModel
import ru.kassi.onlinekassa.presentation.loginFragment.coordinator.LoginFragmentCoordinator
import ru.kassi.onlinekassa.presentation.mainFragment.MainFragmentViewModel
import ru.kassi.onlinekassa.presentation.mainFragment.coordinator.MainFragmentCoordinator
import ru.kassi.onlinekassa.presentation.webviewFragment.WebviewCoordinator
import ru.kassi.onlinekassa.presentation.webviewFragment.WebviewViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class ViewModelFactory @Inject constructor(
    private val resourceManager: ResourceManager,
    private val mainCoordinator: MainFragmentCoordinator,
    private val loginCoordinator: LoginFragmentCoordinator,
    private val webviewCoordinator: WebviewCoordinator,
    private val authRepository: AuthRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val remoteConfigUseCase: FetchRemoteConfigUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            MainFragmentViewModel::class.java -> {
                MainFragmentViewModel(
                    coordinator = mainCoordinator,
                    resources = resourceManager,
                    dispatcher = ioDispatcher,
                    remoteConfigUseCase = remoteConfigUseCase
                )
            }
            LoginViewModel::class.java -> {
                LoginViewModel(
                    coordinator = loginCoordinator,
                    resources = resourceManager,
                    dispatcher = ioDispatcher
                )
            }
            WebviewViewModel::class.java -> {
                WebviewViewModel(
                    coordinator = webviewCoordinator
                )
            }
            else -> {
                error("No factory for ${modelClass.simpleName}")
            }
        } as T
    }
}
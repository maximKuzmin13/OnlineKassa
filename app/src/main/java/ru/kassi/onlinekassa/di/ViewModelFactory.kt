package ru.kassi.onlinekassa.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineDispatcher
import ru.kassi.onlinekassa.data.ResourceManager
import ru.kassi.onlinekassa.domain.FetchRemoteConfigUseCase
import ru.kassi.onlinekassa.presentation.loginFragment.LoginViewModel
import ru.kassi.onlinekassa.presentation.loginFragment.coordinator.LoginFragmentCoordinator
import ru.kassi.onlinekassa.presentation.pinFragment.coordinator.PinCoordinator
import ru.kassi.onlinekassa.presentation.mainFragment.MainFragmentViewModel
import ru.kassi.onlinekassa.presentation.mainFragment.coordinator.MainFragmentCoordinator
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class ViewModelFactory @Inject constructor(
    private val resourceManager: ResourceManager,
    private val mainCoordinator: MainFragmentCoordinator,
    private val loginCoordinator: LoginFragmentCoordinator,
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
                    remoteConfigUseCase
                )
            }
            LoginViewModel::class.java -> {
                LoginViewModel(
                    coordinator = loginCoordinator,
                    resources = resourceManager,
                    dispatcher = ioDispatcher,
                )
            }

            else -> {
                error("No factory for ${modelClass.simpleName}")
            }
        } as T
    }
}
package ru.kassi.onlinekassa.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.kassi.onlinekassa.navigation.StartUpCoordinator
import ru.kassi.onlinekassa.navigation.StartUpCoordinatorImpl
import ru.kassi.onlinekassa.presentation.loginFragment.coordinator.LoginFragmentCoordinator
import ru.kassi.onlinekassa.presentation.loginFragment.coordinator.LoginFragmentCoordinatorImpl
import ru.kassi.onlinekassa.presentation.mainFragment.coordinator.MainFragmentCoordinator
import ru.kassi.onlinekassa.presentation.mainFragment.coordinator.MainFragmentCoordinatorImpl

@Module
@InstallIn(SingletonComponent::class)
interface CoordinatorsModule {

    @Binds
    fun mainFragmentCoordinator(impl: MainFragmentCoordinatorImpl): MainFragmentCoordinator
    @Binds
    fun loginFragmentCoordinator(impl: LoginFragmentCoordinatorImpl): LoginFragmentCoordinator
    @Binds
    fun startUpCoordinator(impl: StartUpCoordinatorImpl): StartUpCoordinator
}
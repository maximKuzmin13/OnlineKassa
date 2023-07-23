package ru.kassi.onlinekassa.presentation.loginFragment.di

import dagger.Binds
import dagger.Module
import ru.kassi.onlinekassa.presentation.loginFragment.coordinator.LoginFragmentCoordinator
import ru.kassi.onlinekassa.presentation.loginFragment.coordinator.LoginFragmentCoordinatorImpl

@Module
abstract class CoordinatorModule {

    @Binds
    abstract fun loginFragmentCoordinator(impl: LoginFragmentCoordinatorImpl): LoginFragmentCoordinator

}
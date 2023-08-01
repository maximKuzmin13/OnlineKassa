package ru.kassi.onlinekassa.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.kassi.onlinekassa.navigation.StartUpCoordinator
import ru.kassi.onlinekassa.navigation.StartUpCoordinatorImpl
import ru.kassi.onlinekassa.presentation.loginFragment.coordinator.LoginFragmentCoordinator
import ru.kassi.onlinekassa.presentation.loginFragment.coordinator.LoginFragmentCoordinatorImpl
import ru.kassi.onlinekassa.presentation.pinFragment.coordinator.PinCoordinator
import ru.kassi.onlinekassa.presentation.pinFragment.coordinator.PinCoordinatorImpl
import ru.kassi.onlinekassa.presentation.mainFragment.coordinator.MainFragmentCoordinator
import ru.kassi.onlinekassa.presentation.mainFragment.coordinator.MainFragmentCoordinatorImpl
import ru.kassi.onlinekassa.presentation.kassiFragment.coordinator.KassiCoordinator
import ru.kassi.onlinekassa.presentation.kassiFragment.coordinator.KassiCoordinatorImpl
import ru.kassi.onlinekassa.presentation.pdfFragment.coordinator.PdfCoordinator
import ru.kassi.onlinekassa.presentation.pdfFragment.coordinator.PdfCoordinatorImpl
import ru.kassi.onlinekassa.presentation.profileFragment.coordinator.ProfileCoordinator
import ru.kassi.onlinekassa.presentation.profileFragment.coordinator.ProfileCoordinatorImpl

@Module
@InstallIn(SingletonComponent::class)
interface CoordinatorsModule {

    @Binds
    fun mainFragmentCoordinator(impl: MainFragmentCoordinatorImpl): MainFragmentCoordinator
    @Binds
    fun loginFragmentCoordinator(impl: LoginFragmentCoordinatorImpl): LoginFragmentCoordinator
    @Binds
    fun pinFragmentCoordinator(impl: PinCoordinatorImpl): PinCoordinator
    @Binds
    fun kassiFragmentCoordinator(impl: KassiCoordinatorImpl): KassiCoordinator
    @Binds
    fun profileFragmentCoordinator(impl: ProfileCoordinatorImpl): ProfileCoordinator
    @Binds
    fun pdfFragmentCoordinator(impl: PdfCoordinatorImpl): PdfCoordinator
    @Binds
    fun startUpCoordinator(impl: StartUpCoordinatorImpl): StartUpCoordinator
}
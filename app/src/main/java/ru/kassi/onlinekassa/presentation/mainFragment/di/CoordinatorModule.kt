package ru.kassi.onlinekassa.presentation.mainFragment.di

import dagger.Binds
import dagger.Module
import ru.kassi.onlinekassa.presentation.mainFragment.coordinator.MainFragmentCoordinator
import ru.kassi.onlinekassa.presentation.mainFragment.coordinator.MainFragmentCoordinatorImpl

@Module
abstract class CoordinatorModule {

    @Binds
    abstract fun mainFragmentCoordinator(impl: MainFragmentCoordinatorImpl): MainFragmentCoordinator

}
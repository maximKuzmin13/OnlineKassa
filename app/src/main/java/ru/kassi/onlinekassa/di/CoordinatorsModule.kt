package ru.kassi.onlinekassa.di

import dagger.Binds
import dagger.Module
import ru.kassi.onlinekassa.presentation.mainFragment.flow.MainFlowCoordinator
import ru.kassi.onlinekassa.presentation.mainFragment.flow.MainFlowCoordinatorImpl

@Module
abstract class CoordinatorsModule {

    @Binds
    abstract fun mainFragmentFlowCoordinator(impl: MainFlowCoordinatorImpl): MainFlowCoordinator
}
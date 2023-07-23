package ru.kassi.onlinekassa.presentation.mainFragment.di

import ru.kassi.onlinekassa.presentation.mainFragment.flow.MainFlowCoordinator

interface MainFragmentDeps {

    fun mainFlowCoordinator(): MainFlowCoordinator
}
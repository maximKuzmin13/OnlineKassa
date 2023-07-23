package ru.kassi.onlinekassa.presentation.loginFragment.di

import ru.kassi.onlinekassa.presentation.loginFragment.flow.LoginFlowCoordinator

interface LoginFragmentDeps {

    fun loginFlowCoordinator(): LoginFlowCoordinator
}
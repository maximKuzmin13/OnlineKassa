package ru.kassi.onlinekassa.presentation.loginFragment.flow

import ru.kassi.onlinekassa.di.ComponentHolder
import ru.kassi.onlinekassa.presentation.loginFragment.di.LoginFragmentComponent

abstract class LoginFlowCoordinator {


    fun exit(componentHolder: ComponentHolder<LoginFragmentComponent>) {
        componentHolder.destroy()
        quit()
    }

    protected abstract fun quit()
}
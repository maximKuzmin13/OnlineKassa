package ru.kassi.onlinekassa.presentation.mainFragment.flow

import ru.kassi.onlinekassa.di.ComponentHolder
import ru.kassi.onlinekassa.presentation.mainFragment.di.MainFragmentComponent

abstract class MainFlowCoordinator {

    abstract fun goToLogin()

    fun exit(componentHolder: ComponentHolder<MainFragmentComponent>) {
        componentHolder.destroy()
        quit()
    }

    protected abstract fun quit()
}
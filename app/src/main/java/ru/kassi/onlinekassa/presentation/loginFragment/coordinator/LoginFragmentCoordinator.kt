package ru.kassi.onlinekassa.presentation.loginFragment.coordinator

interface LoginFragmentCoordinator {

    fun goToMain()

    fun goToMail()
    fun goToAuth()
    fun goToRegister()

    fun goToPin()
    fun exit()
}
package ru.kassi.onlinekassa.presentation.mainFragment.coordinator

interface MainFragmentCoordinator {

    fun goToLogin()

    fun goToKassa(id: Int)

    fun goToProfile()

    fun exit()
}
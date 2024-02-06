package ru.kassi.onlinekassa.presentation.mainFragment.coordinator

interface MainFragmentCoordinator {

    fun goToLogin()

    fun goToKassa(id: String)

    fun goToWebView(link: String)

    fun goToProfile()

    fun exit()
}
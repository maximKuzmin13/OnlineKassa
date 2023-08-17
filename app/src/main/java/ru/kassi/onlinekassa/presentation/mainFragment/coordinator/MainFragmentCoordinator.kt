package ru.kassi.onlinekassa.presentation.mainFragment.coordinator

interface MainFragmentCoordinator {

    fun goToLogin()

    fun goToKassa(id: Int)

    fun goToWebView(link: String)

    fun goToProfile()

    fun exit()
}
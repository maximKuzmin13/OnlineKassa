package ru.kassi.onlinekassa.presentation.kassiFragment.coordinator

interface KassiCoordinator {

    fun goToMain()

    fun goToPdf(num: String)

    fun exit()
}
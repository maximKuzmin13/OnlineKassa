package ru.kassi.onlinekassa.presentation.kassiFragment.coordinator

interface KassiCoordinator {

    fun goToMain()

    fun goToPdf(model: String, type: Boolean?, num: String)

    fun exit()
}
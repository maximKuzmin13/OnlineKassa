package ru.kassi.onlinekassa.navigation

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class StartUpCoordinatorImpl @Inject constructor(
    @MainNavigation private val router: Router
) {

    fun start(screenToOpen: FragmentScreen) {
        router.newRootScreen(screenToOpen)
    }

    fun closeCurrentScreen() {
        router.exit()
    }

    fun navigateTo(screenToOpen: FragmentScreen) {
        router.navigateTo(screenToOpen)
    }
}
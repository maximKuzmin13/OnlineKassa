package ru.kassi.onlinekassa.navigation

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class StartUpCoordinatorImpl @Inject constructor(
    @MainNavigation private val router: Router
): StartUpCoordinator {

    override fun goToMainOrAuth() {
        router.newChain(Screens.LoginScreen())
    }
}
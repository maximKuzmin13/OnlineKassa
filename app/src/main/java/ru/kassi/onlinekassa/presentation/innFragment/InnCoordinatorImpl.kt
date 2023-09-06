package ru.kassi.onlinekassa.presentation.innFragment

import com.github.terrakok.cicerone.Router
import ru.kassi.onlinekassa.navigation.MainNavigation
import ru.kassi.onlinekassa.navigation.Screens
import javax.inject.Inject

class InnCoordinatorImpl @Inject constructor(
    @MainNavigation private val router: Router,
): InnCoordinator {

    override fun goToAuth(inn: String) {
        router.navigateTo(Screens.AuthScreen(inn))
    }

    override fun goToStart() {
        router.navigateTo(Screens.LoginScreen())
    }
}
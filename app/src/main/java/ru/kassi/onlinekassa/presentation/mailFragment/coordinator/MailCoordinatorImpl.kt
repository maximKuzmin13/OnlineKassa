package ru.kassi.onlinekassa.presentation.mailFragment.coordinator

import com.github.terrakok.cicerone.Router
import ru.kassi.onlinekassa.navigation.MainNavigation
import ru.kassi.onlinekassa.navigation.Screens
import javax.inject.Inject

class MailCoordinatorImpl @Inject constructor(
    @MainNavigation private val router: Router,
) : MailCoordinator {

    override fun goToMain() {
        router.navigateTo(Screens.MainScreen())
    }

    override fun goToAuth() {
        router.navigateTo(Screens.LoginScreen())
    }

    override fun goToPin() {
        router.navigateTo(Screens.PinScreen())
    }
}
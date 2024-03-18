package ru.kassi.onlinekassa.presentation.loginFragment.coordinator

import com.github.terrakok.cicerone.Router
import ru.kassi.onlinekassa.navigation.MainNavigation
import ru.kassi.onlinekassa.navigation.Screens
import ru.kassi.onlinekassa.presentation.pinFragment.coordinator.PinCoordinator
import javax.inject.Inject

class LoginFragmentCoordinatorImpl @Inject constructor(
    @MainNavigation private val router: Router,
) : LoginFragmentCoordinator {
    override fun goToMain() {
        router.newRootChain(Screens.MainScreen())
    }

    override fun goToMail() {
        router.navigateTo(Screens.MailScreen())
    }

    override fun goToAuth() {
        router.navigateTo(Screens.InnScreen())
    }

    override fun goToRegister() {
        router.navigateTo(Screens.RegisterScreen())
    }

    override fun exit() {
    }

}
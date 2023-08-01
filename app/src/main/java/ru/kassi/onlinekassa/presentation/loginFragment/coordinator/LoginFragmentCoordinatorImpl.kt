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
        router.navigateTo(Screens.MainScreen())
    }

    override fun goToPin() {
        router.navigateTo(Screens.PinScreen())
    }

    override fun exit() {
    }

}
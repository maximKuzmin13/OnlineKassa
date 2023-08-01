package ru.kassi.onlinekassa.presentation.pinFragment.coordinator

import com.github.terrakok.cicerone.Router
import ru.kassi.onlinekassa.navigation.MainNavigation
import ru.kassi.onlinekassa.navigation.Screens
import javax.inject.Inject

class PinCoordinatorImpl @Inject constructor(
    @MainNavigation private val router: Router,
) : PinCoordinator {
    override fun goToMain() {
        router.navigateTo(Screens.MainScreen())
    }

    override fun exit() {
    }

}
package ru.kassi.onlinekassa.presentation.registrationFragment

import com.github.terrakok.cicerone.Router
import ru.kassi.onlinekassa.navigation.MainNavigation
import ru.kassi.onlinekassa.navigation.Screens
import javax.inject.Inject

class RegistrationCoordinatorImpl @Inject constructor(
    @MainNavigation private val router: Router,
): RegistrationCoordinator {
    override fun goToPin() {
        router.newRootChain(Screens.PinScreen())
    }
}
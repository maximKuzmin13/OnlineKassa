package ru.kassi.onlinekassa.presentation.authFragment

import com.github.terrakok.cicerone.Router
import ru.kassi.onlinekassa.navigation.MainNavigation
import ru.kassi.onlinekassa.navigation.Screens
import javax.inject.Inject

class AuthCoordinatorImpl @Inject constructor(
    @MainNavigation private val router: Router,
): AuthCoordinator {
    override fun goToMail() {
        router.newRootChain(Screens.MainScreen())
    }
}
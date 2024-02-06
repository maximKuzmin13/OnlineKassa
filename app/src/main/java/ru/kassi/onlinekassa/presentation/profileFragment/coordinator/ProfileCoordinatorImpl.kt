package ru.kassi.onlinekassa.presentation.profileFragment.coordinator

import com.github.terrakok.cicerone.Router
import ru.kassi.onlinekassa.navigation.MainNavigation
import ru.kassi.onlinekassa.navigation.Screens
import javax.inject.Inject

class ProfileCoordinatorImpl @Inject constructor(
    @MainNavigation private val router: Router,
) : ProfileCoordinator {

    override fun goToMain() {
        router.navigateTo(Screens.MainScreen())
    }

    override fun exit() {
    }

}
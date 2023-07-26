package ru.kassi.onlinekassa.presentation.mainFragment.coordinator

import com.github.terrakok.cicerone.Router
import ru.kassi.onlinekassa.navigation.MainNavigation
import ru.kassi.onlinekassa.navigation.Screens
import javax.inject.Inject

class MainFragmentCoordinatorImpl @Inject constructor(
    @MainNavigation private val router: Router,
) : MainFragmentCoordinator {

    override fun goToLogin() {
        router.navigateTo(Screens.LoginScreen())
    }

    override fun exit() {
    }

}
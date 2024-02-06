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

    override fun goToKassa(id: String) {
        router.navigateTo(Screens.KassaScreen(id))
    }

    override fun goToWebView(link: String) {
        router.navigateTo(Screens.WebviewScreen(link))
    }

    override fun goToProfile() {
        router.navigateTo(Screens.ProfileScreen())
    }

    override fun exit() {
    }

}
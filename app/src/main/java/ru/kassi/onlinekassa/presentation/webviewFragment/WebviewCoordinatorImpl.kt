package ru.kassi.onlinekassa.presentation.webviewFragment

import com.github.terrakok.cicerone.Router
import ru.kassi.onlinekassa.navigation.MainNavigation
import ru.kassi.onlinekassa.navigation.Screens
import javax.inject.Inject

class WebviewCoordinatorImpl  @Inject constructor(
    @MainNavigation private val router: Router,
) : WebviewCoordinator {
    override fun exit() {
        router.exit()
    }

    override fun back() {
        router.navigateTo(Screens.MainScreen())
    }
}
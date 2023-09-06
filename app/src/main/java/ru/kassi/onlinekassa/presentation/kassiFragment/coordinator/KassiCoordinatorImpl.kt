package ru.kassi.onlinekassa.presentation.kassiFragment.coordinator

import com.github.terrakok.cicerone.Router
import ru.kassi.onlinekassa.navigation.MainNavigation
import ru.kassi.onlinekassa.navigation.Screens
import ru.kassi.onlinekassa.presentation.kassiFragment.coordinator.KassiCoordinator
import javax.inject.Inject

class KassiCoordinatorImpl @Inject constructor(
    @MainNavigation private val router: Router,
) : KassiCoordinator {

    override fun goToMain() {
        router.navigateTo(Screens.MainScreen())
    }

    override fun goToPdf(num: String) {
        router.navigateTo(Screens.PdfScreen(num))
    }

    override fun exit() {
    }

}
package ru.kassi.onlinekassa.presentation.pdfFragment.coordinator

import com.github.terrakok.cicerone.Router
import ru.kassi.onlinekassa.navigation.MainNavigation
import ru.kassi.onlinekassa.navigation.Screens
import javax.inject.Inject

class PdfCoordinatorImpl @Inject constructor(
    @MainNavigation private val router: Router,
): PdfCoordinator {
    override fun backToKassi(num: String) {
        router.backTo(Screens.KassaScreen(num))
    }

    override fun exit() {}
}
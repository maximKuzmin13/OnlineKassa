package ru.kassi.onlinekassa.presentation.mainFragment.flow

import com.github.terrakok.cicerone.Router
import ru.kassi.onlinekassa.navigation.MainNavigation
import ru.kassi.onlinekassa.navigation.FlowScreens
import javax.inject.Inject

class MainFlowCoordinatorImpl @Inject constructor(
    @MainNavigation private val router: Router
): MainFlowCoordinator() {

    override fun goToLogin() {
        router.navigateTo(FlowScreens.Login())
    }

    override fun quit() {
        router.exit()
    }
}
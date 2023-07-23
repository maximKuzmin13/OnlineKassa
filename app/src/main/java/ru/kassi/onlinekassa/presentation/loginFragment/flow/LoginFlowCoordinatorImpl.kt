package ru.kassi.onlinekassa.presentation.loginFragment.flow

import com.github.terrakok.cicerone.Router
import ru.kassi.onlinekassa.navigation.MainNavigation
import javax.inject.Inject

class LoginFlowCoordinatorImpl @Inject constructor(
    @MainNavigation private val router: Router
): LoginFlowCoordinator() {

    override fun quit() {
        router.exit()
    }
}
package ru.kassi.onlinekassa.presentation.mailFragment.coordinator

import android.content.SharedPreferences
import com.github.terrakok.cicerone.Router
import ru.kassi.onlinekassa.di.FirstRunQualifier
import ru.kassi.onlinekassa.di.UserDataQualifier
import ru.kassi.onlinekassa.navigation.MainNavigation
import ru.kassi.onlinekassa.navigation.Screens
import javax.inject.Inject

class MailCoordinatorImpl @Inject constructor(
    @MainNavigation private val router: Router,
    @FirstRunQualifier private val prefs: SharedPreferences,
) : MailCoordinator {

    override fun goToMain() {
        router.navigateTo(Screens.MainScreen())
    }

    override fun goToAuth() {
        router.navigateTo(Screens.LoginScreen())
    }

    override fun goToPin() {
        router.navigateTo(Screens.PinScreen(prefs.getBoolean("firstAuth", false)))
    }
}
package ru.kassi.onlinekassa.presentation.registrationFragment

import android.content.SharedPreferences
import com.github.terrakok.cicerone.Router
import ru.kassi.onlinekassa.di.FirstRunQualifier
import ru.kassi.onlinekassa.navigation.MainNavigation
import ru.kassi.onlinekassa.navigation.Screens
import javax.inject.Inject

class RegistrationCoordinatorImpl @Inject constructor(
    @MainNavigation private val router: Router,
    @FirstRunQualifier private val prefs: SharedPreferences
): RegistrationCoordinator {
    override fun goToPin() {
        router.newRootChain(Screens.PinScreen(prefs.getBoolean("firstAuth", false)))
    }
}
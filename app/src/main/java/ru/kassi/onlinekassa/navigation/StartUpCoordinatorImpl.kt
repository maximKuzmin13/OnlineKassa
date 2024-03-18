package ru.kassi.onlinekassa.navigation

import android.content.SharedPreferences
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.kassi.onlinekassa.di.FirstRunQualifier
import javax.inject.Inject

class StartUpCoordinatorImpl @Inject constructor(
    @MainNavigation private val router: Router,
    @FirstRunQualifier private val firstRunPrefs: SharedPreferences
): StartUpCoordinator {

    override fun goToMainOrAuth() {
        router.newChain(Screens.PinScreen(firstRunPrefs.getBoolean("firstAuth", false)))
    }
}
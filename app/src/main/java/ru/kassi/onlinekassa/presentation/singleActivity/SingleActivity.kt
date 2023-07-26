package ru.kassi.onlinekassa.presentation.singleActivity

import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import dagger.hilt.android.AndroidEntryPoint
import ru.kassi.onlinekassa.R
import ru.kassi.onlinekassa.di.ViewModelFactory
import ru.kassi.onlinekassa.navigation.MainActivityNavigator
import ru.kassi.onlinekassa.navigation.MainNavigation
import ru.kassi.onlinekassa.navigation.StartUpCoordinator
import ru.kassi.onlinekassa.presentation.base.BaseActivity
import javax.inject.Inject

@AndroidEntryPoint
class SingleActivity : BaseActivity() {

    @Inject
    lateinit var startUpCoordinator: StartUpCoordinator

    @Inject
    @MainNavigation
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator: Navigator = MainActivityNavigator(this)

    @Inject
    lateinit var factory: ViewModelFactory

    override val view: Int = R.layout.activity_main

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
        startUpCoordinator.goToMainOrAuth()
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

}

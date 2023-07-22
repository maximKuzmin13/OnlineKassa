package ru.kassi.onlinekassa.navigation

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder

class CiceroneTuner(
    private val navigatorHolder: NavigatorHolder,
    private val navigator: Navigator
) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun addNavigator() {
        navigatorHolder.setNavigator(navigator)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun removeNavigator() {
        navigatorHolder.removeNavigator()
    }

}
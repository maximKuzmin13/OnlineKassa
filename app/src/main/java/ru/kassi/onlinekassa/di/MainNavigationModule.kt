package ru.kassi.onlinekassa.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Cicerone.Companion.create
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import ru.kassi.onlinekassa.navigation.MainNavigation
import javax.inject.Singleton

@Module
class MainNavigationModule {

    @MainNavigation
    private val cicerone: Cicerone<Router> = create()

    @Provides
    @Singleton
    @MainNavigation
    fun provideRouter(): Router {
        return cicerone.router
    }

    @Provides
    @Singleton
    @MainNavigation
    fun provideNavigatorHolder(): NavigatorHolder {
        return cicerone.getNavigatorHolder()
    }
}
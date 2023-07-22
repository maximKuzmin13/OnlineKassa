package ru.kassi.onlinekassa.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import ru.kassi.onlinekassa.navigation.MainNavigation

@Module
class MainNavigationModule {

    @Provides
    @Singleton
    @MainNavigation
    fun provideCicerone(): Cicerone<Router> = Cicerone.create()

    @Provides
    @MainNavigation
    fun provideNavigatorHolder(@MainNavigation cicerone: Cicerone<Router>): NavigatorHolder =
        cicerone.getNavigatorHolder()

    @Provides
    @MainNavigation
    fun provideRouter(@MainNavigation cicerone: Cicerone<Router>): Router = cicerone.router

}
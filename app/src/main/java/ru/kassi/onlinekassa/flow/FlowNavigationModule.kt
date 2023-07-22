package ru.kassi.onlinekassa.flow

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import ru.kassi.onlinekassa.navigation.FeatureScope
import ru.kassi.onlinekassa.navigation.FlowNavigation

@Module
class FlowNavigationModule {

    @Provides
    @FlowNavigation
    @FeatureScope
    fun provideCicerone(): Cicerone<Router> = Cicerone.create()

    @Provides
    @FlowNavigation
    fun provideNavigatorHolder(@FlowNavigation cicerone: Cicerone<Router>): NavigatorHolder = cicerone.getNavigatorHolder()

    @Provides
    @FlowNavigation
    fun provideRouter(@FlowNavigation cicerone: Cicerone<Router>): Router = cicerone.router

}
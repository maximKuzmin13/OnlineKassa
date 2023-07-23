package ru.kassi.onlinekassa.presentation.loginFragment.di

import dagger.Module
import dagger.Provides
import ru.kassi.onlinekassa.di.ComponentHolder
import ru.kassi.onlinekassa.navigation.FeatureScope
import javax.inject.Singleton

@Module
class ComponentHolderModule {

    @Provides
    @FeatureScope
    fun provideComponentHolder(): ComponentHolder<LoginFragmentComponent> = ComponentHolder()

}
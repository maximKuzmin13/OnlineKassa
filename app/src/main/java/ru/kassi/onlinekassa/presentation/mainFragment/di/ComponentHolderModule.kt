package ru.kassi.onlinekassa.presentation.mainFragment.di

import dagger.Module
import dagger.Provides
import ru.kassi.onlinekassa.di.ComponentHolder
import ru.kassi.onlinekassa.navigation.FeatureScope

@Module
class ComponentHolderModule {

    @Provides
    @FeatureScope
    fun provideComponentHolder(): ComponentHolder<MainFragmentComponent> = ComponentHolder()

}
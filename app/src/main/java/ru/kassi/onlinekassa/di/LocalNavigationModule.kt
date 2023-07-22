package ru.kassi.onlinekassa.di

import dagger.Module
import dagger.Provides
import ru.kassi.onlinekassa.navigation.LocalCiceroneHolder
import javax.inject.Singleton

@Module
class LocalNavigationModule {

    @Provides
    @Singleton
    fun provideLocalNavigationHolder(): LocalCiceroneHolder = LocalCiceroneHolder()
}
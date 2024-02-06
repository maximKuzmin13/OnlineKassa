package ru.kassi.onlinekassa.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.kassi.onlinekassa.navigation.LocalCiceroneHolder
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalNavigationModule {

    @Provides
    @Singleton
    fun provideLocalNavigationHolder(): LocalCiceroneHolder = LocalCiceroneHolder()
}
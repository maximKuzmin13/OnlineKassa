package ru.kassi.onlinekassa.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
class PrefsModule {

    @Provides
    @FirstRunQualifier
    fun providePrefs(@ApplicationContext context: Context): SharedPreferences = context.getSharedPreferences("GlobalPrefs", Context.MODE_PRIVATE)

    @Provides
    @UserDataQualifier
    fun provideUserDataPrefs(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
    }
}

@Qualifier
@Retention
annotation class FirstRunQualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class UserDataQualifier

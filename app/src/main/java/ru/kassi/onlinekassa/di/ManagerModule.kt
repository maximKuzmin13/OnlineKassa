package ru.kassi.onlinekassa.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.kassi.onlinekassa.data.ResourceManager
import ru.kassi.onlinekassa.data.ResourceManagerImpl

@Module
@InstallIn(SingletonComponent::class)
class ManagerModule {

    @Provides
    fun provideResourceManager(@ApplicationContext appContext: Context): ResourceManager {
        return ResourceManagerImpl(appContext)
    }
}
package ru.kassi.onlinekassa.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.kassi.onlinekassa.data.ResourceManager
import ru.kassi.onlinekassa.data.ResourceManagerImpl
import ru.kassi.onlinekassa.domain.LoadKassaInfoUseCase
import ru.kassi.onlinekassa.domain.api.kassa.KassaRepository

@Module
@InstallIn(SingletonComponent::class)
class ManagerModule {

    @Provides
    fun provideResourceManager(@ApplicationContext appContext: Context): ResourceManager {
        return ResourceManagerImpl(appContext)
    }

    @Provides
    fun provideUseCase(repository: KassaRepository): LoadKassaInfoUseCase {
        return LoadKassaInfoUseCase(repository)
    }
}
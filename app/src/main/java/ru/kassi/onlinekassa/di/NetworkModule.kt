package ru.kassi.onlinekassa.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kassi.onlinekassa.domain.api.auth.AuthApi
import ru.kassi.onlinekassa.domain.api.innCheck.InnCheckApi
import ru.kassi.onlinekassa.domain.api.points.PointApi
import ru.kassi.onlinekassa.domain.api.profile.ProfileApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("http://109.201.65.98:8080/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideAuthApi(retrofit: Retrofit): AuthApi =
        retrofit.create(AuthApi::class.java)

    @Singleton
    @Provides
    fun provideInnApi(retrofit: Retrofit): InnCheckApi =
        retrofit.create(InnCheckApi::class.java)

    @Singleton
    @Provides
    fun providePointApi(retrofit: Retrofit): PointApi =
        retrofit.create(PointApi::class.java)

    @Singleton
    @Provides
    fun provideProfileApi(retrofit: Retrofit): ProfileApi =
        retrofit.create(ProfileApi::class.java)
}
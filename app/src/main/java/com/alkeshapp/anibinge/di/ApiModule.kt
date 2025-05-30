package com.alkeshapp.anibinge.di

import com.alkeshapp.anibinge.domain.repository.AnimeRepository
import com.alkeshapp.anibinge.data.repository.AnimeRepositoryImpl
import com.alkeshapp.anibinge.data.services.AnimeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    @Singleton
    @Provides
    fun provideAnimeRepository(apiService: AnimeService): AnimeRepository = AnimeRepositoryImpl(apiService)

    @Singleton
    @Provides
    fun provideRetrofitClient(): AnimeService {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(AnimeService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(AnimeService::class.java)
    }
}
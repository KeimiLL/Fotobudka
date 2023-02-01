package com.mobilne.foto_zabawa.di

import com.mobilne.foto_zabawa.network.ApiInterface
import com.mobilne.foto_zabawa.network.OAuthInterceptor
import com.mobilne.foto_zabawa.repository.TestRepository
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
class AppModule {
    private val BASE_URL = "https://fotobudka.advers.pl/"
    private val TOKEN = "vCNjQhm6jy4gyYMWugs76mzNQZjVPzC7"

    @Singleton
    @Provides
    fun provideTestRepository(
        api: ApiInterface
    ) = TestRepository(api)

    @Singleton
    @Provides
    fun providesApi(): ApiInterface {
        val okHttpClient: OkHttpClient
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(OAuthInterceptor("Bearer", TOKEN))
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiInterface::class.java)
    }
}
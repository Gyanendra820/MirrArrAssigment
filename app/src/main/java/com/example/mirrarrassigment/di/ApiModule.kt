package com.example.mirrarrassigment.di

import com.app.haryanacustomerportal.di.LAP
import com.example.mirrarrassigment.service.AuthenticationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    /*URL*/
    private external fun baseUrl(): String



    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }

        @JvmStatic
        external fun apiKey(): String
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        @LAP httpClient: OkHttpClient,
        gsonConverterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl())
            .addConverterFactory(gsonConverterFactory)
            .client(httpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthService(retrofit: Retrofit): AuthenticationService =
        retrofit.create(AuthenticationService::class.java)
}



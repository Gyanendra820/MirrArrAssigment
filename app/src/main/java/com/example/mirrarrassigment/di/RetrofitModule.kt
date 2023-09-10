package com.example.mirrarrassigment.di

import androidx.viewbinding.BuildConfig
import com.app.haryanacustomerportal.di.LAP
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class RetrofitModule {

    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }

    @Singleton
    @Provides
    fun provideConverterFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().serializeNulls().create()
    }


    @Provides
    @Singleton
    @LAP
    fun provideClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClients = OkHttpClient.Builder()


        httpClients.readTimeout(10, TimeUnit.MINUTES)
        httpClients.writeTimeout(10, TimeUnit.MINUTES)
        httpClients.connectTimeout(10, TimeUnit.MINUTES)

        httpClients.addInterceptor(OkHttpProfilerInterceptor())

        httpClients.addInterceptor { chain ->
            val request: Request =
                chain.request().newBuilder()
                    .build()
            chain.proceed(request)
        }

        if (BuildConfig.DEBUG) {
            httpClients.addInterceptor(logging)
        }

        return httpClients.build()


    }
}



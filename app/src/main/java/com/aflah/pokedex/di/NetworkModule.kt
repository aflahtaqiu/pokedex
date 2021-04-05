package com.aflah.pokedex.di

import com.aflah.pokedex.BuildConfig
import com.aflah.pokedex.data.network.ApiEndpoint
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .writeTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    single {
        if (BuildConfig.DEBUG) {
            Retrofit.Builder()
                .client(get<OkHttpClient>())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiEndpoint.BASE_URL)
                .build()
        } else {
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiEndpoint.BASE_URL)
                .build()
        }
    }

    single {
        get<Retrofit>().create(ApiEndpoint::class.java)
    }
}
package com.aflah.pokedex

import android.app.Application
import com.aflah.pokedex.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(applicationContext)
            modules(localModule)
            modules(networkModule)
            modules(dataSourceModule)
            modules(repositoryModule)
            modules(viewModelModule)
        }
    }
}
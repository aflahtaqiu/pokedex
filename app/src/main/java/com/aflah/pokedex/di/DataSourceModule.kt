package com.aflah.pokedex.di

import com.aflah.pokedex.data.local.LocalDataSource
import com.aflah.pokedex.data.network.NetworkDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single { NetworkDataSource(get()) }
    single { LocalDataSource(get()) }
}
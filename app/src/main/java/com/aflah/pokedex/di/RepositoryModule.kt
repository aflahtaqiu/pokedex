package com.aflah.pokedex.di

import com.aflah.pokedex.data.repository.PokemonRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { PokemonRepository(get(), get()) }
}
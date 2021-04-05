package com.aflah.pokedex.di

import androidx.room.Room
import com.aflah.pokedex.data.local.PokedexDatabase
import com.aflah.pokedex.data.local.TypeResponseConverter
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localModule= module {
    single {
        TypeResponseConverter()
    }

    single {
        Room.databaseBuilder(
                androidApplication(),
                PokedexDatabase::class.java,
                "pokemonDB"
        ).allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .addTypeConverter(get<TypeResponseConverter>())
                .build()
    }

    single {
        get<PokedexDatabase>().pokemonDao()
    }
}
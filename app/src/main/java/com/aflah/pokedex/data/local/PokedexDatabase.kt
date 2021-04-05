package com.aflah.pokedex.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aflah.pokedex.model.Pokemon
import com.aflah.pokedex.model.response.EvolutionResponse
import com.aflah.pokedex.model.response.PokemonSpeciesResponse

@Database(entities = [Pokemon::class, EvolutionResponse::class, PokemonSpeciesResponse::class], version = 1, exportSchema = false)
@TypeConverters(value = [TypeResponseConverter::class])
abstract class PokedexDatabase: RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}
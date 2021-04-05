package com.aflah.pokedex.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aflah.pokedex.model.Pokemon
import com.aflah.pokedex.model.response.EvolutionResponse
import com.aflah.pokedex.model.response.PokemonSpeciesResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonList(pokemons: List<Pokemon>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonDetail(pokemon: Pokemon)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvolutionChain(evolChain: EvolutionResponse)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonSpecies(species: PokemonSpeciesResponse)

    @Query("SELECT * FROM pokemon")
    fun getPokemonList(): Flow<List<Pokemon>>

    @Query("SELECT * FROM pokemon WHERE name = :name")
    fun getDetailPokemon(name: String): Flow<Pokemon>

    @Query("SELECT * FROM evolutionresponse WHERE id = :id")
    fun getEvolutionChain(id: Int): Flow<EvolutionResponse>

    @Query("SELECT * FROM pokemonspeciesresponse WHERE id = :id")
    fun getPokemonSpecies(id: Int): Flow<PokemonSpeciesResponse>
}
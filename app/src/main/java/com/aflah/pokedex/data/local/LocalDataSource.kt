package com.aflah.pokedex.data.local

import com.aflah.pokedex.model.Pokemon
import com.aflah.pokedex.model.response.EvolutionResponse
import com.aflah.pokedex.model.response.PokemonSpeciesResponse
import kotlinx.coroutines.flow.Flow

class LocalDataSource constructor(private val dao: PokemonDao) {

    fun getAllPokemon(): Flow<List<Pokemon>> = dao.getPokemonList()

    fun getDetailPokemon(pokemonName: String): Flow<Pokemon> = dao.getDetailPokemon(pokemonName)

    fun getEvolutionChain(id: Int): Flow<EvolutionResponse> = dao.getEvolutionChain(id)

    fun getPokemonSpecies(id: Int): Flow<PokemonSpeciesResponse> = dao.getPokemonSpecies(id)

    suspend fun savePokemonList(pokemons: List<Pokemon>) {
        dao.insertPokemonList(pokemons)
    }

    suspend fun saveDetailPokemon(pokemon: Pokemon) {
        dao.insertPokemonDetail(pokemon)
    }

    suspend fun saveEvolutionChain(evolChain: EvolutionResponse) {
        dao.insertEvolutionChain(evolChain)
    }

    suspend fun savePokemonSpecies(species: PokemonSpeciesResponse) {
        dao.insertPokemonSpecies(species)
    }
}
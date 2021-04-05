package com.aflah.pokedex.data.network

import com.aflah.pokedex.base.BaseDataSource

class NetworkDataSource (private val apiEndpoint: ApiEndpoint): BaseDataSource() {

    suspend fun getAllPokemons(offset: Int) = getResult {
        apiEndpoint.fetchPokemonList(offset)
    }

    suspend fun getPokemonEvolutionChain(evolutionChainId: Int) = getResult {
        apiEndpoint.fetchPokemonEvolutionChain(evolutionChainId)
    }

    suspend fun getPokemonSpecies(pokemonId: Int) = getResult {
        apiEndpoint.fetchPokemonSpecies(pokemonId)
    }

    suspend fun getPokemonDetail(pokemonName: String) = getResult {
        apiEndpoint.fetchPokemonDetail(pokemonName)
    }
}
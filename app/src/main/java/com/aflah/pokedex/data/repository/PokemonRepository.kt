package com.aflah.pokedex.data.repository

import com.aflah.pokedex.base.BaseRepository
import com.aflah.pokedex.data.local.LocalDataSource
import com.aflah.pokedex.data.network.NetworkDataSource
import com.aflah.pokedex.model.Pokemon

class PokemonRepository  constructor(
        private val local: LocalDataSource,
        private val network: NetworkDataSource
) : BaseRepository() {

    suspend fun fetchPokemons(offset: Int = 0) = singleSource(
            databaseQuery = { local.getAllPokemon() },
            networkCall = { network.getAllPokemons(offset) },
            saveCallResult = {
                local.savePokemonList(it.results.map { pokemon ->
                    Pokemon(
                        name = pokemon.name,
                        url = pokemon.url,
                        id = pokemon.getPokemonId()
                    )
                })
            }
    )

    suspend fun fetchDetailPokemon(name: String) = singleSource(
        databaseQuery = { local.getDetailPokemon(name) },
        networkCall = { network.getPokemonDetail(name) },
        saveCallResult = { pokemon ->
            local.saveDetailPokemon(
                Pokemon(
                    name = pokemon.name,
                    height = pokemon.height,
                    weight = pokemon.weight,
                    id = pokemon.id,
                    url = pokemon.getPokemonUrl(pokemon.id)
                )
            )
        }
    )

    suspend fun fetchPokemonSpecies(id: Int) = singleSource(
        databaseQuery = { local.getPokemonSpecies(id) },
        networkCall = { network.getPokemonSpecies(id) },
        saveCallResult = { response ->
            local.savePokemonSpecies(response)
        }
    )

    suspend fun fetchEvolveChain(id: Int) = singleSource(
        databaseQuery = { local.getEvolutionChain(id) },
        networkCall = { network.getPokemonEvolutionChain(id) },
        saveCallResult = { evolutionResponse ->
            local.saveEvolutionChain(evolutionResponse)
        }
    )
}
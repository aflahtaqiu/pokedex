package com.aflah.pokedex.data.network

import com.aflah.pokedex.model.Pokemon
import com.aflah.pokedex.model.response.EvolutionResponse
import com.aflah.pokedex.model.response.PokemonListResponse
import com.aflah.pokedex.model.response.PokemonSpeciesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndpoint {

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
    }

    @GET("pokemon")
    suspend fun fetchPokemonList(
            @Query("offset") offset: Int,
            @Query("limit") limit: Int = 20
    ): Response<PokemonListResponse>

    @GET("pokemon/{name}")
    suspend fun fetchPokemonDetail(@Path("name") pokemonName: String) : Response<Pokemon>

    @GET("pokemon-species/{id}")
    suspend fun fetchPokemonSpecies(
        @Path("id") pokemonId: Int
    ): Response<PokemonSpeciesResponse>

    @GET("evolution-chain/{id}")
    suspend fun fetchPokemonEvolutionChain(
        @Path("id") evolutionChainId: Int
    ): Response<EvolutionResponse>
}
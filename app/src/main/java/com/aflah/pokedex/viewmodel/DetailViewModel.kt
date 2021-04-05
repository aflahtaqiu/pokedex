package com.aflah.pokedex.viewmodel

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aflah.pokedex.base.BaseViewModel
import com.aflah.pokedex.data.repository.PokemonRepository
import com.aflah.pokedex.model.EvolutionChain
import com.aflah.pokedex.model.Pokemon
import com.aflah.pokedex.model.ResultState
import com.aflah.pokedex.model.response.EvolutionResponse
import com.aflah.pokedex.model.response.PokemonSpeciesResponse
import com.aflah.pokedex.ui.MainActivity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: PokemonRepository): BaseViewModel() {

    var evolutionChainApiResponse = MutableLiveData<ResultState<EvolutionResponse>>()
    var evolutionChainResult = MutableLiveData<List<Pokemon>>()

    var pokemonSpeciesApiResponse = MutableLiveData<ResultState<PokemonSpeciesResponse>>()

    var pokemonDetailApiResponse = MutableLiveData<ResultState<Pokemon>>()
    var pokemonDetailResult = MutableLiveData<Pokemon>()

    private val evolutionPokemonList = mutableListOf<Pokemon>()

    private var pokemonName: String = ""
    private var pokemonId: Int = 0

    fun processIntent(intent: Intent) {
        intent.extras?.let {
            pokemonName = it.getString(MainActivity.KEY_BUNDLE_POKEMON_NAME) ?: ""
            pokemonId = it.getInt(MainActivity.KEY_BUNDLE_POKEMON_ID)

            getPokemonDetail(pokemonName)
            getPokemonSpecies(pokemonId)
        }
    }

    private fun getEvolutionChain(pokemonId: Int) {
        viewModelScope.launch {
            repository.fetchEvolveChain(pokemonId).collect { response ->
                evolutionChainApiResponse.value = response
            }
        }
    }

    fun handleEvolutionApiResponse(result: ResultState<EvolutionResponse>) {
        when(result.status) {
            ResultState.Status.LOADING -> isLoading.value = true
            ResultState.Status.SUCCESS -> {
                isLoading.value = false
                result.data?.let {
                    iterateChain(it.chain)
                }
                evolutionChainResult.value = evolutionPokemonList
            }
            ResultState.Status.ERROR -> {
                isLoading.value = false
                message.value = result.message
            }
        }
    }

    private fun iterateChain(chain: EvolutionChain) {
        evolutionPokemonList.add(chain.species)
        if (chain.evolveTo.isNotEmpty()) {
            iterateChain(chain.evolveTo.first())
        }
    }

    fun getPokemonSpecies(pokemonId: Int) {
        viewModelScope.launch {
            repository.fetchPokemonSpecies(pokemonId).collect { response ->
                pokemonSpeciesApiResponse.value = response
            }
        }
    }

    fun handlePokemonSpeciesApiResponse(result: ResultState<PokemonSpeciesResponse>) {
        when(result.status) {
            ResultState.Status.LOADING -> isLoading.value = true
            ResultState.Status.SUCCESS -> {
                isLoading.value = false
                result.data?.let {
                    getEvolutionChain(it.evolChain.getEvolutionChainId())
                }
            }
            ResultState.Status.ERROR -> {
                isLoading.value = false
                message.value = result.message
            }
        }
    }

    private fun getPokemonDetail(pokemonName: String) {
        viewModelScope.launch {
           repository.fetchDetailPokemon(pokemonName).collect {
               pokemonDetailApiResponse.value = it
           }
        }
    }

    fun handlePokemonDetailApiResponse(result: ResultState<Pokemon>) {
        when(result.status) {
            ResultState.Status.LOADING -> isLoading.value = true
            ResultState.Status.SUCCESS -> {
                isLoading.value = false
                result.data?.let {
                    pokemonDetailResult.value = it
                }
            }
            ResultState.Status.ERROR -> {
                isLoading.value = false
                message.value = result.message
            }
        }
    }
}
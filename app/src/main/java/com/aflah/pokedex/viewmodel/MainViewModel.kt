package com.aflah.pokedex.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aflah.pokedex.base.BaseViewModel
import com.aflah.pokedex.data.repository.PokemonRepository
import com.aflah.pokedex.model.Pokemon
import com.aflah.pokedex.model.ResultState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel (private val repository: PokemonRepository): BaseViewModel() {

    var pokemonsApiResponse= MutableLiveData<ResultState<List<Pokemon>>>()
    var pokemonsResult = MutableLiveData<List<Pokemon>>()

    var offset = MutableLiveData<Int>()

    fun getPokemonList(offset: Int = 0) {
        viewModelScope.launch {
            repository.fetchPokemons(offset).collect {
                pokemonsApiResponse.value = it
            }
        }
    }

    fun handlePokemonsApiResponse(result: ResultState<List<Pokemon>>) {
        when(result.status) {
            ResultState.Status.LOADING -> isLoading.value = true
            ResultState.Status.SUCCESS -> {
                isLoading.value = false
                result.data?.let { data ->
                    pokemonsResult.value = data
                    offset.value = data.size
                }
            }
            ResultState.Status.ERROR -> {
                isLoading.value = false
                message.value = result.message
            }
        }
    }
}
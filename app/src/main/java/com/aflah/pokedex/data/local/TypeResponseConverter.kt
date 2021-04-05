package com.aflah.pokedex.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.aflah.pokedex.model.EvolutionChain
import com.aflah.pokedex.model.Pokemon
import com.aflah.pokedex.model.response.PokemonSpeciesResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class TypeResponseConverter {

  @TypeConverter
  fun fromDetailPokemonString(value: String): List<Pokemon.TypeResponse>? {
    val gson = Gson()
    val type = object : TypeToken<List<Pokemon.TypeResponse>>() {}.type
    return gson.fromJson(value, type)
  }

  @TypeConverter
  fun fromDetailPokemonType(type: List<Pokemon.TypeResponse>?): String {
    val gson = Gson()
    val value = object : TypeToken<List<Pokemon.TypeResponse>>() {}.type
    return gson.toJson(type, value)
  }

  @TypeConverter
  fun fromEvolChainString(value: String): PokemonSpeciesResponse.EvolChain? {
    val gson = Gson()
    val type = object : TypeToken<PokemonSpeciesResponse.EvolChain>() {}.type
    return gson.fromJson(value, type)
  }

  @TypeConverter
  fun fromEvolChainype(type: PokemonSpeciesResponse.EvolChain?): String {
    val gson = Gson()
    val value = object : TypeToken<PokemonSpeciesResponse.EvolChain>() {}.type
    return gson.toJson(type, value)
  }

  @TypeConverter
  fun fromEvolutionChainString(value: String): EvolutionChain? {
    val gson = Gson()
    val type = object : TypeToken<EvolutionChain>() {}.type
    return gson.fromJson(value, type)
  }

  @TypeConverter
  fun fromEvolutionChainype(type: EvolutionChain?): String {
    val gson = Gson()
    val value = object : TypeToken<EvolutionChain>() {}.type
    return gson.toJson(type, value)
  }
}

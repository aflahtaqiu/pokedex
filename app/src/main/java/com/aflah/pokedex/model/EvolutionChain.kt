package com.aflah.pokedex.model

import com.google.gson.annotations.SerializedName

data class EvolutionChain(
    @SerializedName("is_baby")
    val isBaby: Boolean,
    val species: Pokemon,
    @SerializedName("evolves_to")
    val evolveTo: List<EvolutionChain>
)
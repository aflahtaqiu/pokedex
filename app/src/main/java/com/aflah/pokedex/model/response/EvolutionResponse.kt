package com.aflah.pokedex.model.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aflah.pokedex.model.EvolutionChain

@Entity
data class EvolutionResponse (
    @PrimaryKey
    val id: Int,
    val chain: EvolutionChain
)
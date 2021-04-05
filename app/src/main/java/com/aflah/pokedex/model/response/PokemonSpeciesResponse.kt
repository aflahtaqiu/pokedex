package com.aflah.pokedex.model.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class PokemonSpeciesResponse (
        @PrimaryKey
        val id: Int,
        val name: String,
        @SerializedName("evolution_chain")
        val evolChain: EvolChain
) {
    inner class EvolChain(val url : String) {
        fun getEvolutionChainId(): Int {
            return url.split("/".toRegex()).dropLast(1).last().toInt()
        }
    }
}
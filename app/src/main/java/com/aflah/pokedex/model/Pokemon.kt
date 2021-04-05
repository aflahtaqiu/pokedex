package com.aflah.pokedex.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlin.random.Random

@Entity
data class Pokemon (
        val id: Int,
        val height: Int = 0,
        val weight: Int = 0,
        @PrimaryKey(autoGenerate = false)
        val name: String,
        val url: String,
        val types: List<TypeResponse> = listOf(),
        val hp: Int = Random.nextInt(MAX_HP),
        val attack: Int = Random.nextInt(MAX_ATTACK),
        val defense: Int = Random.nextInt(MAX_DEFENCE),
        val speed: Int = Random.nextInt(MAX_SPEED),
        val exp: Int = Random.nextInt(MAX_EXP)
) {

    @Ignore
    val next: String = ""

    fun getWeightString() = String.format("%.1f KG", weight.toFloat() / 10)
    fun getHeightString() = String.format("%.1f M", height.toFloat() / 10)
    fun getHpString(): String = "$hp/$MAX_HP"
    fun getAttackString(): String = "$attack/$MAX_ATTACK"
    fun getDefenseString(): String = "$defense/$MAX_DEFENCE"
    fun getSpeedString(): String = "$speed/$MAX_SPEED"
    fun getExpString(): String = "$exp/$MAX_EXP"

    fun getImageUrl(): String {
        return "https://pokeres.bastionbot.org/images/pokemon/${getPokemonId()}.png"
    }

    fun getPokemonId(): Int {
        return url.split("/".toRegex()).dropLast(1).last().toInt()
    }

    fun getPokemonUrl(id: Int): String {
        return "https://pokeapi.co/api/v2/pokemon/$id/"
    }

    data class TypeResponse(
            val slot: Int,
            val type: Type
    )

    data class Type(
            val name: String
    )

    companion object {
        const val MAX_HP = 300
        const val MAX_ATTACK = 300
        const val MAX_DEFENCE = 300
        const val MAX_SPEED = 300
        const val MAX_EXP = 1000
    }
}
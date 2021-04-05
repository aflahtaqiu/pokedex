package com.aflah.pokedex.model.response

import com.aflah.pokedex.model.Pokemon

data class PokemonListResponse(
        val results: List<Pokemon>
) : BaseResponse()
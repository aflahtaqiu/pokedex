package com.aflah.pokedex.model.response

open class BaseResponse(
        val count: Int? = 0,
        val next: String = "",
        val previous: String? = ""
)
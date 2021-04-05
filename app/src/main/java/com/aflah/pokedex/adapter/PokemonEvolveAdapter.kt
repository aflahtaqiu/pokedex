package com.aflah.pokedex.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.aflah.pokedex.R
import com.aflah.pokedex.base.RecyclerAdapter
import com.aflah.pokedex.extension.inflate
import com.aflah.pokedex.extension.loadUrl
import com.aflah.pokedex.model.Pokemon
import kotlinx.android.synthetic.main.item_evolve_pokemon.view.*

class PokemonEvolveAdapter(context: Context, data: MutableList<Pokemon>): RecyclerAdapter<Pokemon, PokemonEvolveAdapter.PokemonEvolveViewHolder>(context, data) {

    inner class PokemonEvolveViewHolder(itemView: View): BaseViewHolder(itemView) {
        override fun bind(item: Pokemon) {
            with(itemView) {
                evolvePokemonImageView.loadUrl(item.getImageUrl())
                evolvePokemonTextView.text = item.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonEvolveViewHolder {
        val view = parent.inflate(R.layout.item_evolve_pokemon)
        return PokemonEvolveViewHolder(view)
    }
}
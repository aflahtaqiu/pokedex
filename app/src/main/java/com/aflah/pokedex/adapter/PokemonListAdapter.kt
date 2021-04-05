package com.aflah.pokedex.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.aflah.pokedex.R
import com.aflah.pokedex.adapter.PokemonListAdapter.PokemonListViewHolder
import com.aflah.pokedex.base.RecyclerAdapter
import com.aflah.pokedex.extension.inflate
import com.aflah.pokedex.extension.loadUrl
import com.aflah.pokedex.model.Pokemon
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonListAdapter(context: Context, data: MutableList<Pokemon>, private val onItemClicked: (Int, String) -> Unit): RecyclerAdapter<Pokemon, PokemonListViewHolder>(context, data) {

    inner class PokemonListViewHolder(itemView: View): BaseViewHolder(itemView) {
        override fun bind(item: Pokemon) {
            with(itemView) {
                pokemonImageView.loadUrl(item.getImageUrl())
                pokemonNameTextView.text = item.name
                setOnClickListener {
                    onItemClicked.invoke(item.getPokemonId(), item.name)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        val view = parent.inflate(R.layout.item_pokemon)
        return PokemonListViewHolder(view)
    }
}
package com.aflah.pokedex.ui

import android.content.Intent
import androidx.lifecycle.Observer
import com.aflah.pokedex.R
import com.aflah.pokedex.adapter.PokemonListAdapter
import com.aflah.pokedex.base.BaseActivity
import com.aflah.pokedex.databinding.ActivityMainBinding
import com.aflah.pokedex.extension.infiniteScrollListener
import com.aflah.pokedex.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    var adapter: PokemonListAdapter? = null

    override val layoutResource = R.layout.activity_main

    override fun bindViewModel() {
        viewModel = getViewModel()
    }

    override fun viewDidLoad() {
        binding.activity = this
        viewModel.getPokemonList()
        registerObserver()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val onItemClicked: (Int, String) -> Unit = { pokemonId, pokemonName ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra(KEY_BUNDLE_POKEMON_ID, pokemonId)
                putExtra(KEY_BUNDLE_POKEMON_NAME, pokemonName)
            }
            startActivity(intent)
        }

        adapter = PokemonListAdapter(this, mutableListOf(), onItemClicked)
    }

    private fun registerObserver() {
        viewModel.pokemonsApiResponse.observe(this, Observer {
            viewModel.handlePokemonsApiResponse(it)
        })
        viewModel.pokemonsResult.observe(this, Observer {
            adapter?.addItems(it)
        })
        viewModel.offset.observe(this, Observer {
            pokemonRecyclerView.infiniteScrollListener(1) {
                viewModel.getPokemonList(it)
            }
        })
    }

    companion object {
        const val KEY_BUNDLE_POKEMON_NAME = "pokemon_name"
        const val KEY_BUNDLE_POKEMON_ID = "pokemon_id"
    }
}
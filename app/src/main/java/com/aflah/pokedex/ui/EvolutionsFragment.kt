package com.aflah.pokedex.ui

import androidx.lifecycle.Observer
import com.aflah.pokedex.R
import com.aflah.pokedex.adapter.PokemonEvolveAdapter
import com.aflah.pokedex.base.BaseFragment
import com.aflah.pokedex.databinding.FragmentEvolutionsBinding
import com.aflah.pokedex.viewmodel.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class EvolutionsFragment : BaseFragment<FragmentEvolutionsBinding, DetailViewModel>() {

    var adapter: PokemonEvolveAdapter? = null
    private val sharedViewModel by sharedViewModel<DetailViewModel>()

    override val layoutResource: Int
        get() = R.layout.fragment_evolutions

    override fun bindViewModel() {
        viewModel = sharedViewModel
    }

    override fun viewDidLoad() {
        binding.fragment = this
        setupRecyclerView()
        registerObserver()
    }

    private fun setupRecyclerView() {
        context?.let { ctx ->
            adapter = PokemonEvolveAdapter(ctx, mutableListOf())
        }
    }

    private fun registerObserver() {
        viewModel.evolutionChainResult.observe(this, Observer {
            adapter?.addItems(it)
        })
    }
}
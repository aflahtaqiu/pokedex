package com.aflah.pokedex.ui

import androidx.lifecycle.Observer
import com.aflah.pokedex.R
import com.aflah.pokedex.base.BaseFragment
import com.aflah.pokedex.databinding.FragmentStatsBinding
import com.aflah.pokedex.viewmodel.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class StatsFragment : BaseFragment<FragmentStatsBinding, DetailViewModel>() {

    private val sharedViewModel by sharedViewModel<DetailViewModel>()

    override val layoutResource: Int
        get() = R.layout.fragment_stats

    override fun bindViewModel() {
        viewModel = sharedViewModel
    }

    override fun viewDidLoad() {
        registerObserver()
    }

    private fun registerObserver() {
        sharedViewModel.pokemonDetailResult.observe(this, Observer {
            binding.pokemon = it
        })
    }
}
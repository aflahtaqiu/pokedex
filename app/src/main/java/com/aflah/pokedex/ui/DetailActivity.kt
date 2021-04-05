package com.aflah.pokedex.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.aflah.pokedex.R
import com.aflah.pokedex.base.BaseActivity
import com.aflah.pokedex.databinding.ActivityDetailBinding
import com.aflah.pokedex.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {

    override val layoutResource: Int
        get() = R.layout.activity_detail

    override fun bindViewModel() {
        viewModel = getViewModel()
    }

    override fun viewDidLoad() {
        viewModel.processIntent(intent)
        registerObserver()
        setupClickListener()
        statsButton.performClick()
    }

    private fun setupClickListener() {
        statsButton.setOnClickListener {
            switchFragment(StatsFragment())
        }
        evolveButton.setOnClickListener {
            switchFragment(EvolutionsFragment())
        }
    }

    private fun registerObserver() {
        viewModel.evolutionChainApiResponse.observe(this, Observer {
            viewModel.handleEvolutionApiResponse(it)
        })

        viewModel.pokemonSpeciesApiResponse.observe(this, Observer {
            viewModel.handlePokemonSpeciesApiResponse(it)
        })

        viewModel.pokemonDetailApiResponse.observe(this, Observer {
            viewModel.handlePokemonDetailApiResponse(it)
        })

        viewModel.pokemonDetailResult.observe(this, Observer {
            it?.let {
                binding.pokemon = it
            }
        })
    }

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.detailPokemonFrameLayout, fragment, "")
                .commit()
    }
}
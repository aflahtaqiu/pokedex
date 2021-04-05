package com.aflah.pokedex.di

import com.aflah.pokedex.viewmodel.DetailViewModel
import com.aflah.pokedex.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}
package com.example.homework6_1.di

import com.example.homework6_1.ui.fragments.character.CharactersViewModel
import com.example.homework6_1.ui.fragments.details.CharactersDetailedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { CharactersViewModel(get()) }
    viewModel { CharactersDetailedViewModel(get()) }
}
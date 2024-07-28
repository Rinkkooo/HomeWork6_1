package com.example.homework6_1.di

import org.koin.dsl.module

val appModule = module {
    includes(networkModule, repositoryModule, viewModelsModule)
}
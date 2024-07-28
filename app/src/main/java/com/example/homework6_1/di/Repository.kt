package com.example.homework6_1.di

import com.example.homework6_1.data.repository.CartoonRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory {
        CartoonRepository(get())
    }
}
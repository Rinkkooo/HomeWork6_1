package com.example.homework6_1.di

import com.example.homework6_1.data.api.CartoonApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        provideApiService(get())
    }
    single {
        provideRetrofit(get())
    }
    single {
        provideOkHttpClient(get())
    }
    single {
        provideLoggingInterceptor()
    }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl("https://rickandmortyapi.com/api/")
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .build()


fun provideApiService(retrofit: Retrofit): CartoonApiService =
    retrofit.create(CartoonApiService::class.java)


fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
    OkHttpClient.Builder()
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
}
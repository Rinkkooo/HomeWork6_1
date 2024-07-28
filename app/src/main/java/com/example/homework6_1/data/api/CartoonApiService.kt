package com.example.homework6_1.data.api

import com.example.homework6_1.data.model.BaseResponse
import com.example.homework6_1.data.model.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CartoonApiService {

    @GET("character")
    suspend fun getCharacters(): Response<BaseResponse>

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Response<Character>
}
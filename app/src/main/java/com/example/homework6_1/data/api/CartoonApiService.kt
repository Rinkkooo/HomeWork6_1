package com.example.homework6_1.data.api

import com.example.homework6_1.data.model.BaseResponse
import com.example.homework6_1.data.model.Character
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CartoonApiService {

    @GET("character")
    fun getCharacters(): Call<BaseResponse>

    @GET("character/{id}")
    fun getCharacterById(@Path("id") id: Int): Call<Character>
}
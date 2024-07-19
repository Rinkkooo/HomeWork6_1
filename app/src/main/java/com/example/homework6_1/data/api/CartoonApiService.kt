package com.example.homework6_1.data.api

import com.example.homework6_1.data.model.BaseResponse
import retrofit2.Call
import retrofit2.http.GET

interface CartoonApiService {

    @GET("character")
    fun getCharacters(): Call<BaseResponse>

}
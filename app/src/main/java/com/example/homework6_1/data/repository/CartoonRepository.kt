package com.example.homework6_1.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.homework6_1.data.api.CartoonApiService
import com.example.homework6_1.data.base.BaseRepository
import com.example.homework6_1.data.model.Character
import com.example.homework6_1.utils.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import java.io.IOException

class CartoonRepository(
    private val cartoonApiService: CartoonApiService
) : BaseRepository() {
    fun getCharacters(): LiveData<Resource<List<Character>>> = doRequest {
        cartoonApiService.getCharacters().characters
    }
}


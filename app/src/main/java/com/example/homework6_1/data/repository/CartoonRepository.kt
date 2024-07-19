package com.example.homework6_1.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.homework6_1.data.api.CartoonApiService
import com.example.homework6_1.data.model.BaseResponse
import com.example.homework6_1.data.model.Character
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CartoonRepository @Inject constructor(
    private val cartoonApiService: CartoonApiService
) {
    fun getCharacters(): LiveData<List<Character>> {

        val data = MutableLiveData<List<Character>>()
        cartoonApiService.getCharacters().enqueue(object : Callback<BaseResponse> {

            override fun onResponse(p0: Call<BaseResponse>, p1: Response<BaseResponse>) {
                data.postValue(p1.body()!!.characters)
            }

            override fun onFailure(p0: Call<BaseResponse>, p1: Throwable) {
                print(p1.message)
            }
        })
        return data
    }
}












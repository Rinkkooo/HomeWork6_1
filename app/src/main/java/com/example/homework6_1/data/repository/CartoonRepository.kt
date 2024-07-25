package com.example.homework6_1.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.homework6_1.data.api.CartoonApiService
import com.example.homework6_1.data.model.BaseResponse
import com.example.homework6_1.data.model.Character
import com.example.homework6_1.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CartoonRepository @Inject constructor(
    private val cartoonApiService: CartoonApiService
) {
    fun getCharacters(): LiveData<Resource<List<Character>>> {

        val data = MutableLiveData<Resource<List<Character>>>()

        data.postValue(Resource.Loading())

        cartoonApiService.getCharacters().enqueue(object : Callback<BaseResponse> {

            override fun onResponse(p0: Call<BaseResponse>, p1: Response<BaseResponse>) {
                data.postValue(Resource.Success(p1.body()!!.characters))
            }

            override fun onFailure(p0: Call<BaseResponse>, p1: Throwable) {
                data.postValue(Resource.Error(p1.message!!))
            }
        })
        return data
    }

    fun getCharacterById(id: Int): LiveData<Resource<Character>> {
        val data = MutableLiveData<Resource<Character>>()
        data.postValue(Resource.Loading())

        cartoonApiService.getCharacterById(id).enqueue(object : Callback<Character> {
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        data.postValue(Resource.Success(it))
                    } ?: run {
                        data.postValue(Resource.Error("Response body is null"))
                    }
                } else {
                    data.postValue(Resource.Error("Failed to get character by id"))
                }
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                data.postValue(Resource.Error(t.message ?: "Unknown error"))
            }
        })

        return data
    }

}












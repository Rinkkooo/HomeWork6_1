package com.example.homework6_1.data.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.homework6_1.utils.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import java.io.IOException

abstract class BaseRepository {

    protected fun <T> doRequest(request: suspend () -> T) : LiveData<Resource<T>> = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val response = request()
            if (response != null) {
                emit(Resource.Success(response))
            } else {
                emit(Resource.Error("Server error"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(handleException(e)))
        }
    }

    private fun handleException(e: Exception): String {
        return when (e) {
            is IOException -> e.localizedMessage ?: "Network error"
            is HttpException -> e.localizedMessage ?: "Server error"
            else -> "Unknown error"
        }
    }

}
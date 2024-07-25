package com.example.homework6_1.data.repository

import com.example.homework6_1.data.api.CartoonApiService
import com.example.homework6_1.data.model.Character
import com.example.homework6_1.utils.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CartoonRepository @Inject constructor(
    private val cartoonApiService: CartoonApiService
) {
    suspend fun getCharacters(): Resource<List<Character>> {
        return try {
            val response = cartoonApiService.getCharacters()
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!.characters)
            } else {
                Resource.Error("Unexpected error")
            }
        } catch (e: IOException) {
            Resource.Error(e.localizedMessage)
        } catch (e: HttpException) {
            Resource.Error(e.localizedMessage)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage)
        }
    }

    suspend fun getCharacterById(id: Int): Resource<Character> {
        return try {
            val response = cartoonApiService.getCharacterById(id)
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Unexpected error")
            }
        } catch (e: IOException) {
            Resource.Error(e.localizedMessage)
        } catch (e: HttpException) {
            Resource.Error(e.localizedMessage)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage)
        }
    }
}

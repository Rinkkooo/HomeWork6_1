package com.example.homework6_1.ui.fragments.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework6_1.data.model.Character
import com.example.homework6_1.data.repository.CartoonRepository
import com.example.homework6_1.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersDetailedViewModel @Inject constructor(
    private val repository: CartoonRepository
) : ViewModel() {

    private val _charactersDetails = MutableLiveData<Resource<Character>>()
    val charactersDetails: LiveData<Resource<Character>> get() = _charactersDetails

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private var characterId: Int? = null

    fun setCharacterId(id: Int) {
        characterId = id
        fetchCharacterDetails()
    }

    private fun fetchCharacterDetails() {
        characterId?.let { id ->
            viewModelScope.launch {
                val result = repository.getCharacterById(id)
                _charactersDetails.postValue(result)
            }
        }
    }
}

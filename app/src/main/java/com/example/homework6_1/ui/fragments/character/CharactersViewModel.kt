package com.example.homework6_1.ui.fragments.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework6_1.data.model.Character
import com.example.homework6_1.data.repository.CartoonRepository
import com.example.homework6_1.utils.Resource
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val cartoonRepository: CartoonRepository
) : ViewModel() {

    private val _character = MutableLiveData<Resource<List<Character>>>()
    val character: LiveData<Resource<List<Character>>> get() = _character

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        cartoonRepository.getCharacters().observeForever { resource ->
            _character.postValue(resource)
        }
    }
}

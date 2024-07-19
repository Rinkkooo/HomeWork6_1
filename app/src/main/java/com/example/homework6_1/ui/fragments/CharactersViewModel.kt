package com.example.homework6_1.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homework6_1.data.model.Character
import com.example.homework6_1.data.repository.CartoonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val cartoonRepository: CartoonRepository
) : ViewModel() {

    private val _character = MutableLiveData<List<Character>>()
    val character: LiveData<List<Character>> get() = _character

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        cartoonRepository.getCharacters().observeForever{ characters ->
            _character.postValue(characters)
        }
    }
}
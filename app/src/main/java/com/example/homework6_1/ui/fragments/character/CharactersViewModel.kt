package com.example.homework6_1.ui.fragments.character

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
class CharactersViewModel @Inject constructor(
    private val cartoonRepository: CartoonRepository
) : ViewModel() {

    private val _character = MutableLiveData<Resource<List<Character>>>()
    val character: LiveData<Resource<List<Character>>> get() = _character

    private val _selectedCharacter = MutableLiveData<Character>()
    val selectedCharacter: LiveData<Character> get() = _selectedCharacter

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        viewModelScope.launch {
            val result = cartoonRepository.getCharacters()
            _character.postValue(result)
        }
    }

    fun selectedCharacter(character: Character) {
        _selectedCharacter.postValue(character)
    }
}

package com.example.homework6_1.ui.fragments.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homework6_1.data.model.Character
import com.example.homework6_1.utils.UiStates

class CharactersDetailedViewModel : ViewModel() {

    private val _charactersDetails = MutableLiveData<UiStates<Character>>(UiStates.Empty())
    val charactersDetails: LiveData<UiStates<Character>> get() = _charactersDetails

}

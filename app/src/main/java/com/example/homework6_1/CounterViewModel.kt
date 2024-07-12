package com.example.homework6_1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel: ViewModel() {

    private val repository = CounterModel()
    private val _counterData = MutableLiveData<Int>()
    val counterData: LiveData<Int> = _counterData

    fun onIncrementClick() {
        repository.increment()
        _counterData.value = repository.getResult()
    }

    fun onDecrementClick() {
        repository.decrement()
        _counterData.value = repository.getResult()
    }

}
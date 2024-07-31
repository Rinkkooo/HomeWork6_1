package com.example.homework6_1.utils

sealed class UiStates <T> {
    class Loading <T> : UiStates<T>()
    class Error<T> (val message: String) : UiStates<T>()
    class Success<T> (val data : T) : UiStates<T>()
    class Empty<T> : UiStates<T>()

}
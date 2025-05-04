package com.example.composesample.model

sealed class UIState<out T> {
    data class Valid<T>(val data: T) : UIState<T>()
    data object Loading : UIState<Nothing>()
}
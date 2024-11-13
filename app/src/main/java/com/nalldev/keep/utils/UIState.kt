package com.nalldev.keep.utils

sealed class UIState<out R>{
    data class Success <out T>(val data: T) : UIState<T>()
    data object Error : UIState<Nothing>()
    data object Loading: UIState<Nothing>()
}
package com.aflah.pokedex.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {

    var message = MutableLiveData<String>()
    var isLoading = MutableLiveData<Boolean>()

    init {
        isLoading.value = false
    }

    fun showLoading(isShow: Boolean = true) {
        isLoading.value = isShow
    }
}
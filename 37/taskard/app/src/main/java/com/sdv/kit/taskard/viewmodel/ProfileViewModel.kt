package com.sdv.kit.taskard.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sdv.kit.taskard.util.AuthStorageUtil

class ProfileViewModel(context: Context) : ViewModel() {
    private val authStorageUtil: AuthStorageUtil by lazy {
        AuthStorageUtil(context)
    }

    private val _isLoggedIn = MutableLiveData(true)
    val isLoggedIn: LiveData<Boolean> = _isLoggedIn

    fun logout() {
        authStorageUtil.removeUserId()
        _isLoggedIn.value = false
    }
}
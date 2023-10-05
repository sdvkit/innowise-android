package com.sdv.kit.application

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sdv.kit.application.entity.User
import com.sdv.kit.application.util.RandomUserGenerator
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CustomViewModel : ViewModel() {
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main

    private val _userList = MutableLiveData(RandomUserGenerator.generate(30))
    val userList: LiveData<List<User>> = _userList

    private val _isProcessing = MutableLiveData(true)
    val isProcessing: LiveData<Boolean> = _isProcessing

    private val _isRefreshing = MutableLiveData(false)
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    fun firstLoad() = CoroutineScope(mainDispatcher).launch {
        _userList.value = listOf()
        delay(6_000)
        _userList.value = RandomUserGenerator.generate(30)
        _isProcessing.value = false
    }

    fun clearList() {
        _userList.value = listOf()
    }

    fun updateList() = CoroutineScope(mainDispatcher).launch {
        _isRefreshing.value = true
        delay(3_000)
        _userList.value = RandomUserGenerator.generate(30)
        _isRefreshing.value = false
    }
}
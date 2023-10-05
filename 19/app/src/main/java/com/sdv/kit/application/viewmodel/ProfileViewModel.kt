package com.sdv.kit.application.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sdv.kit.application.entity.Profile
import com.sdv.kit.application.model.Profiles
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val mainDispatcher = Dispatchers.Main

    private val _currentProfile = MutableLiveData<Profile>()
    val currentProfile: LiveData<Profile> = _currentProfile

    fun startProfileUpdating() = CoroutineScope(mainDispatcher).launch {
        while (true) {
            delay(5_000)
            _currentProfile.value = Profiles.next()
        }
    }
}
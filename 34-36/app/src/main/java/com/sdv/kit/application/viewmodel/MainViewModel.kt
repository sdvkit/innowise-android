package com.sdv.kit.application.viewmodel

import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sdv.kit.application.api.AUTH_PAGE_URI
import com.sdv.kit.application.api.ApiClient
import com.sdv.kit.application.api.util.TokenStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val application: Application
) : AndroidViewModel(application), DefaultLifecycleObserver {
    private val tokenStorage: TokenStorage by lazy { TokenStorage(application) }

    private val _isAuthenticated = MutableLiveData(tokenStorage.isTokenSaved())
    val isAuthenticated: LiveData<Boolean> = _isAuthenticated

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        tryGetAccessToken(owner as AppCompatActivity)
    }

    private fun tryGetAccessToken(activity: AppCompatActivity) = with(activity.intent) {
        val code = data?.getQueryParameter("code")

        if (code != null) CoroutineScope(Dispatchers.Main).launch {
            try {
                val accessToken = ApiClient.authService.getAccessToken(code)
                _isAuthenticated.value = tokenStorage.saveToken(accessToken)
            } catch (_: Exception) {}
        }
    }

    fun tryConnect() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(AUTH_PAGE_URI))
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        application.startActivity(intent)
    }
}
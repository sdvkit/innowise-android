package com.sdv.kit.application.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.sdv.kit.application.api.util.TokenStorage
import com.sdv.kit.application.db.PlacesDatabaseClient
import com.sdv.kit.application.db.dao.PlaceDao
import com.sdv.kit.application.util.SharedPrefUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(context: Context) : ViewModel() {
    private val placeDao: PlaceDao by lazy {
        PlacesDatabaseClient.instance(context).placeDao()
    }
    private val tokenStorage: TokenStorage by lazy {
        TokenStorage(context)
    }
    private val sharedPrefUtil: SharedPrefUtil by lazy {
        SharedPrefUtil(context)
    }

    fun logout() = CoroutineScope(Dispatchers.IO).launch {
        placeDao.deleteAll()
        tokenStorage.removeToken()
        sharedPrefUtil.remove()
    }
}
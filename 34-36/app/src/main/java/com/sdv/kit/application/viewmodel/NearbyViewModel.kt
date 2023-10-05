package com.sdv.kit.application.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sdv.kit.application.api.ApiClient
import com.sdv.kit.application.api.entity.Place
import com.sdv.kit.application.api.util.TokenStorage
import com.sdv.kit.application.db.PlacesDatabaseClient
import com.sdv.kit.application.db.dao.PlaceDao
import com.sdv.kit.application.mapper.PlaceMapper
import com.sdv.kit.application.util.NetworkUtil
import com.sdv.kit.application.util.SharedPrefUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NearbyViewModel(context: Context) : ViewModel() {
    private val placeDao: PlaceDao by lazy {
        PlacesDatabaseClient.instance(context).placeDao()
    }
    private val tokenStorage: TokenStorage by lazy {
        TokenStorage(context)
    }
    private val sharedPrefUtil: SharedPrefUtil by lazy {
        SharedPrefUtil(context)
    }
    private val networkUtil: NetworkUtil by lazy {
        NetworkUtil(context)
    }

    private val _isPlacesLoading = MutableLiveData(false)
    val isPlacesLoading: LiveData<Boolean> = _isPlacesLoading

    private val _places = MutableLiveData(listOf<Place>())
    val places: LiveData<List<Place>> = _places

    fun loadPlaces() = CoroutineScope(Dispatchers.Main).launch {
        _isPlacesLoading.value = true
        _places.value = when {
            networkUtil.isInternetConnectionPresents() -> loadAndCachePlacesFromApi()
            else -> loadPlacesFromCache()
        }
        _isPlacesLoading.value = false
    }

    fun logout() = CoroutineScope(Dispatchers.IO).launch {
        placeDao.deleteAll()
        tokenStorage.removeToken()
        sharedPrefUtil.remove()
    }

    private suspend fun loadPlacesFromCache(): List<Place> = withContext(Dispatchers.IO) {
        placeDao.findAll().map { PlaceMapper.fromDatabaseEntity(it) }
    }

    private suspend fun loadAndCachePlacesFromApi(): List<Place> = withContext(Dispatchers.IO) {
        val places = ApiClient.placeService.getPlaces().result

        launch {
            placeDao.saveAll(places.map { PlaceMapper.toDatabaseEntity(it) })
        }

        return@withContext places
    }
}
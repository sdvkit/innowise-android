package com.sdv.kit.application.api

import com.sdv.kit.application.api.service.AuthService
import com.sdv.kit.application.api.service.PlaceService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

abstract class ApiClient {
    companion object {
        private val retrofitDefaultClient = Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        private val retrofitAuthClient = Retrofit.Builder()
            .baseUrl(BASE_AUTH_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val authService: AuthService by lazy { retrofitAuthClient.create() }
        val placeService: PlaceService by lazy { retrofitDefaultClient.create() }
    }
}
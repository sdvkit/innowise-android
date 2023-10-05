package com.sdv.kit.application.api.service

import com.sdv.kit.application.api.CLIENT_ID
import com.sdv.kit.application.api.CLIENT_SECRET
import com.sdv.kit.application.api.REDIRECT_URI
import com.sdv.kit.application.api.entity.AccessToken
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthService {
    @GET("access_token" +
            "?client_id=$CLIENT_ID" +
            "&client_secret=$CLIENT_SECRET" +
            "&grant_type=authorization_code" +
            "&redirect_uri=$REDIRECT_URI")
    suspend fun getAccessToken(@Query("code") code: String): AccessToken
}
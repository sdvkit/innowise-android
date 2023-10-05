package com.sdv.kit.application.api.service

import com.sdv.kit.application.api.API_KEY
import com.sdv.kit.application.api.LATITUDE
import com.sdv.kit.application.api.LIMIT
import com.sdv.kit.application.api.LONGITUDE
import com.sdv.kit.application.api.RADIUS
import com.sdv.kit.application.api.entity.Place
import com.sdv.kit.application.api.entity.PlaceList
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface PlaceService {
    @Headers(
        "Authorization: $API_KEY",
        "accept: application/json"
    )
    @GET("places/search?ll=$LATITUDE%2C$LONGITUDE&radius=$RADIUS&limit=$LIMIT")
    suspend fun getPlaces(): PlaceList

    @Headers(
        "Authorization: $API_KEY",
        "accept: application/json"
    )
    @GET("places/{fsq_id}/photos")
    suspend fun getPlaceImages(
        @Path("fsq_id") fsqId: String,
        @Query("limit") limit: Int
    ): List<Place.Image>
}
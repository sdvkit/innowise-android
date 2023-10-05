package com.sdv.kit.application.api.entity

import com.google.gson.annotations.SerializedName

@JvmInline
value class PlaceList(@SerializedName("results") val result: List<Place>)
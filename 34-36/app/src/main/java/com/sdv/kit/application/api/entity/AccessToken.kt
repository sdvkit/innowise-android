package com.sdv.kit.application.api.entity

import com.google.gson.annotations.SerializedName

@JvmInline
value class AccessToken(@SerializedName("access_token") val value: String)
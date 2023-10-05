package com.sdv.kit.application.api.entity

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Ignore
import com.google.gson.annotations.SerializedName

data class Place(
    @ColumnInfo(name = "fsq_id")
    @SerializedName("fsq_id")
    val fsqId: String,

    @Ignore
    @SerializedName("categories")
    val categories: List<Category>,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String,

    @Embedded
    @SerializedName("location")
    val location: Location
) {
    constructor(fsqId: String, name: String, location: Location) : this(fsqId, listOf(), name, location)

    data class Category(
        @ColumnInfo(name = "category_id")
        @SerializedName("id")
        val id: Int,

        @ColumnInfo(name = "category_name")
        @SerializedName("name")
        val name: String
    )

    data class Location(
        @ColumnInfo(name = "formatted_address")
        @SerializedName("formatted_address")
        val formattedAddress: String,
    )

    data class Image(
        @SerializedName("prefix") val prefix: String,
        @SerializedName("suffix") val suffix: String,
    ) {
        val imageUri: Uri get() = Uri.parse("${prefix}original$suffix")
    }

}
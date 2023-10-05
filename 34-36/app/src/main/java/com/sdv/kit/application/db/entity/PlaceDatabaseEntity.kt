package com.sdv.kit.application.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sdv.kit.application.api.entity.Place

@Entity(tableName = "places")
class PlaceDatabaseEntity(
    @Embedded
    val place: Place,

    @PrimaryKey(autoGenerate = false)
    val id: String = place.fsqId,

    @Embedded
    val category: Place.Category
)
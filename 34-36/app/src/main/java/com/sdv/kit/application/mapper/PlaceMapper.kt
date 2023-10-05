package com.sdv.kit.application.mapper

import com.sdv.kit.application.api.entity.Place
import com.sdv.kit.application.db.entity.PlaceDatabaseEntity

class PlaceMapper private constructor() {
    companion object {
        fun toDatabaseEntity(place: Place): PlaceDatabaseEntity = PlaceDatabaseEntity(
            place,
            place.fsqId,
            place.categories[0]
        )

        fun fromDatabaseEntity(placeDatabaseEntity: PlaceDatabaseEntity) = Place(
            placeDatabaseEntity.place.fsqId,
            listOf(placeDatabaseEntity.category),
            placeDatabaseEntity.place.name,
            placeDatabaseEntity.place.location
        )
    }
}
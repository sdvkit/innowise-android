package com.sdv.kit.application.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sdv.kit.application.db.dao.PlaceDao
import com.sdv.kit.application.db.entity.PlaceDatabaseEntity

@Database(entities = [PlaceDatabaseEntity::class], version = 1)
abstract class PlacesDatabaseClient : RoomDatabase() {
    abstract fun placeDao(): PlaceDao

    companion object {
        private const val DATABASE_NAME = "foursquare_places.db"
        private var currentInstance: PlacesDatabaseClient? = null

        fun instance(context: Context): PlacesDatabaseClient {
            if (currentInstance == null) currentInstance = Room
                .databaseBuilder(context, PlacesDatabaseClient::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()

            return currentInstance!!
        }
    }
}
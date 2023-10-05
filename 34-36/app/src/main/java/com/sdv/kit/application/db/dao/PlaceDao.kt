package com.sdv.kit.application.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sdv.kit.application.db.entity.PlaceDatabaseEntity

@Dao
interface PlaceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(placeDatabaseEntity: PlaceDatabaseEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(placeDatabaseEntity: List<PlaceDatabaseEntity>)

    @Query("SELECT * FROM places")
    fun findAll(): List<PlaceDatabaseEntity>

    @Query("DELETE FROM places")
    fun deleteAll()
}
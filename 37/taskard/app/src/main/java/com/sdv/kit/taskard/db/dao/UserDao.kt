package com.sdv.kit.taskard.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sdv.kit.taskard.entity.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(user: User)

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun findById(id: String): User?
}
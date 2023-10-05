package com.sdv.kit.taskard.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sdv.kit.taskard.entity.Task
import io.reactivex.rxjava3.core.Flowable

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(task: Task): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg task: Task)

    @Query("SELECT t.* FROM tasks t WHERE t.project_id = :projectId")
    fun findAllByProjectId(projectId: Long): List<Task>

    @Query("SELECT t.* FROM tasks t " +
            "INNER JOIN projects p ON p.id = t.project_id " +
            "WHERE p.user_id = :userId")
    fun findAllByUser(userId: String): Flowable<List<Task>>

    @Query("SELECT t.* FROM tasks t WHERE t.id = :id")
    fun findById(id: Long): Task

    @Update
    fun update(vararg task: Task)

    @Query("DELETE FROM tasks WHERE id = :taskId")
    fun deleteById(taskId: Long)
}
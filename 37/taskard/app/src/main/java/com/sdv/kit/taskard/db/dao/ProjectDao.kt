package com.sdv.kit.taskard.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.sdv.kit.taskard.entity.Project
import com.sdv.kit.taskard.entity.relation.ProjectWithTasks
import io.reactivex.rxjava3.core.Flowable

@Dao
interface ProjectDao {
    @Transaction
    @Query("SELECT p.* FROM projects p WHERE p.user_id = :userId")
    fun findAllByUserIdFlowable(userId: String): Flowable<List<ProjectWithTasks>>

    @Query("SELECT p.* FROM projects p WHERE p.user_id = :userId")
    fun findAllByUserId(userId: String): List<Project>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(project: Project): Long

    @Query("SELECT p.* FROM projects p WHERE p.id = :projectId")
    fun findById(projectId: Long): ProjectWithTasks

    @Query("DELETE FROM projects WHERE id = :projectId")
    fun deleteById(projectId: Long)

    @Update
    fun update(vararg project: Project)
}
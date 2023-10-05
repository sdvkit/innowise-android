package com.sdv.kit.taskard.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "tasks",
    foreignKeys = [
        ForeignKey(
            entity = Project::class,
            parentColumns = ["id"],
            childColumns = ["project_id"],
            onDelete = CASCADE
        )
    ]
)
data class Task(
    @ColumnInfo("id")
    @PrimaryKey(autoGenerate = true)
    val id: Long?,

    @ColumnInfo("project_id")
    val projectId: Long,

    @ColumnInfo("name")
    var name: String,

    @ColumnInfo("description")
    var description: String,

    @ColumnInfo("is_completed")
    var isCompleted: Boolean = false,

    @ColumnInfo("is_favourite")
    var isFavourite: Boolean = false
) {
    class Builder {
        private var id: Long? = null
        private var projectId: Long = 0
        private var name: String = ""
        private var description: String = ""
        private var isCompleted: Boolean = false
        private var isFavourite: Boolean = false

        fun id(id: Long?): Builder = apply { this.id = id }
        fun projectId(projectId: Long): Builder = apply { this.projectId = projectId }
        fun name(name: String): Builder = apply { this.name = name }
        fun description(description: String): Builder = apply { this.description = description }
        fun isCompleted(isCompleted: Boolean): Builder = apply { this.isCompleted = isCompleted }
        fun isFavourite(isFavourite: Boolean): Builder = apply { this.isFavourite = isFavourite }
        fun build(): Task = Task(id, projectId, name, description, isCompleted, isFavourite)
    }
}
package com.sdv.kit.taskard.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "projects")
data class Project(
    @ColumnInfo("id")
    @PrimaryKey(autoGenerate = true)
    val id: Long?,

    @ColumnInfo("user_id")
    val userId: String,

    @ColumnInfo("name")
    var name: String
) {
    class Builder {
        private var id: Long? = null
        private var userId: String = ""
        private var name: String = ""

        fun id(id: Long?): Builder = apply { this.id = id }
        fun userId(userId: String): Builder = apply { this.userId = userId }
        fun name(name: String): Builder = apply { this.name = name }
        fun build(): Project = Project(id, userId, name)
    }
}
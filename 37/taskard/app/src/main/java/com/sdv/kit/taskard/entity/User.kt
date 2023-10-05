package com.sdv.kit.taskard.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @ColumnInfo("id")
    @PrimaryKey(autoGenerate = false)
    val id: String,

    @ColumnInfo("username")
    val username: String,

    @ColumnInfo("email")
    val email: String,

    @ColumnInfo("avatar_url")
    val avatarUrl: String?
) {
    class Builder {
        private var id: String = ""
        private var username: String = ""
        private var email: String = ""
        private var avatarUrl: String = ""

        fun id(id: String) = apply { this.id = id }
        fun username(username: String) = apply { this.username = username }
        fun email(email: String) = apply { this.email = email }
        fun avatarUrl(avatarUrl: String) = apply { this.avatarUrl = avatarUrl }
        fun build(): User = User(id, username, email, avatarUrl)
    }
}
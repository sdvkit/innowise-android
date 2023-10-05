package com.sdv.kit.taskard.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sdv.kit.taskard.db.dao.ProjectDao
import com.sdv.kit.taskard.db.dao.TaskDao
import com.sdv.kit.taskard.db.dao.UserDao
import com.sdv.kit.taskard.entity.Project
import com.sdv.kit.taskard.entity.Task
import com.sdv.kit.taskard.entity.User

@Database(
    entities = [Project::class, Task::class, User::class],
    version = 9
)
abstract class DatabaseClient : RoomDatabase() {
    abstract fun projectDao(): ProjectDao
    abstract fun taskDao(): TaskDao
    abstract fun userDao(): UserDao

    companion object {
        private const val DATABASE_NAME = "taskard.db"
        private var instance: DatabaseClient? = null

        fun instance(context: Context): DatabaseClient {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context, DatabaseClient::class.java, DATABASE_NAME
                ).fallbackToDestructiveMigration().build()
            }
            return instance!!
        }
    }
}
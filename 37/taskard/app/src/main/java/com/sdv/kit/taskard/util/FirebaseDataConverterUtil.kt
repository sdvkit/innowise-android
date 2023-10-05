package com.sdv.kit.taskard.util

import android.util.Log
import com.sdv.kit.taskard.entity.Project
import com.sdv.kit.taskard.entity.Task
import com.sdv.kit.taskard.entity.relation.ProjectWithTasks

class FirebaseDataConverterUtil private constructor() {
    companion object {
        fun fromFirebaseProject(projectValue: Map<String, Any>): ProjectWithTasks {
            val project = Project.Builder()
                .id(projectValue["id"]!! as Long)
                .userId(projectValue["userId"]!! as String)
                .name(projectValue["name"]!! as String)
                .build()

            val tasks = mutableListOf<Task>()

            (projectValue["tasks"] as Map<*, *>).values.forEach { taskValue ->
                val taskMap = taskValue as Map<*, *>

                val task = Task.Builder()
                    .id(taskMap["id"] as Long)
                    .name(taskMap["name"] as String)
                    .description(taskMap["description"] as String)
                    .isCompleted(taskMap["completed"] as Boolean)
                    .isFavourite(taskMap["favourite"] as Boolean)
                    .projectId(taskMap["projectId"]!! as Long)
                    .build()

                tasks.add(task)
            }

            return ProjectWithTasks(project, tasks)
        }
    }
}
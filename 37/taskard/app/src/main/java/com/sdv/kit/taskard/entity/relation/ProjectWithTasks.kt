package com.sdv.kit.taskard.entity.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.sdv.kit.taskard.entity.Project
import com.sdv.kit.taskard.entity.Task

data class ProjectWithTasks(
    @Embedded
    val project: Project,

    @Relation(
        parentColumn = "id",
        entityColumn = "project_id"
    )
    val tasks: List<Task>
)
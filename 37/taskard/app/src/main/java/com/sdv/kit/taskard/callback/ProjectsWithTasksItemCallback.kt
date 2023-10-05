package com.sdv.kit.taskard.callback

import androidx.recyclerview.widget.DiffUtil
import com.sdv.kit.taskard.entity.relation.ProjectWithTasks

class ProjectsWithTasksItemCallback : DiffUtil.ItemCallback<ProjectWithTasks>() {
    override fun areItemsTheSame(oldItem: ProjectWithTasks, newItem: ProjectWithTasks): Boolean =
        oldItem.project.id == newItem.project.id

    override fun areContentsTheSame(oldItem: ProjectWithTasks, newItem: ProjectWithTasks): Boolean =
        oldItem.project == newItem.project
}
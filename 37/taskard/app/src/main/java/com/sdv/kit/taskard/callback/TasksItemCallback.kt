package com.sdv.kit.taskard.callback

import androidx.recyclerview.widget.DiffUtil
import com.sdv.kit.taskard.entity.Task

class TasksItemCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean =
        oldItem == newItem
}
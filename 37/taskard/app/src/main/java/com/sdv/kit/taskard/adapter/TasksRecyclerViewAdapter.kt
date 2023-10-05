package com.sdv.kit.taskard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.sdv.kit.taskard.R
import com.sdv.kit.taskard.callback.TasksItemCallback
import com.sdv.kit.taskard.entity.Task
import com.sdv.kit.taskard.viewholder.TasksViewHolder

class TasksRecyclerViewAdapter(
    context: Context
) : ListAdapter<Task, TasksViewHolder>(TasksItemCallback()) {
    private val inflater: LayoutInflater by lazy {
        LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val view = inflater.inflate(R.layout.view_task_item, parent, false)
        return TasksViewHolder(view)
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) =
        holder.bind(getItem(position))
}
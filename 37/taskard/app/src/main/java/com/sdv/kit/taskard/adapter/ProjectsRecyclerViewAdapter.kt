package com.sdv.kit.taskard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.sdv.kit.taskard.R
import com.sdv.kit.taskard.callback.ProjectsWithTasksItemCallback
import com.sdv.kit.taskard.entity.relation.ProjectWithTasks
import com.sdv.kit.taskard.viewholder.ProjectsViewHolder

class ProjectsRecyclerViewAdapter(
    context: Context
) : ListAdapter<ProjectWithTasks, ProjectsViewHolder>(ProjectsWithTasksItemCallback()) {
    private val inflater: LayoutInflater by lazy {
        LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectsViewHolder {
        val view = inflater.inflate(R.layout.view_project_item, parent, false)
        return ProjectsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectsViewHolder, position: Int) =
        holder.bind(getItem(position))
}
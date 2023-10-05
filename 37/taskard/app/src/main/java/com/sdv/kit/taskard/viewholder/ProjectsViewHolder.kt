package com.sdv.kit.taskard.viewholder

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sdv.kit.taskard.R
import com.sdv.kit.taskard.component.ProjectActionsBottomSheetDialog
import com.sdv.kit.taskard.contract.FirebaseSynchronizer
import com.sdv.kit.taskard.entity.relation.ProjectWithTasks

class ProjectsViewHolder(itemView: View) : ViewHolder(itemView) {
    private val projectNameTextView: TextView = itemView.findViewById(R.id.projectNameTextView)
    private val actionsButton: AppCompatButton = itemView.findViewById(R.id.actionsButton)

    @SuppressLint("SetTextI18n")
    fun bind(projectWithTasks: ProjectWithTasks) {
        projectNameTextView.text = projectWithTasks.project.name
        setClickListeners(projectWithTasks)
    }

    private fun setClickListeners(projectWithTasks: ProjectWithTasks) {
        actionsButton.setOnClickListener {
            ProjectActionsBottomSheetDialog(projectWithTasks)
                .show((itemView.context as AppCompatActivity).supportFragmentManager, null)
        }
    }
}
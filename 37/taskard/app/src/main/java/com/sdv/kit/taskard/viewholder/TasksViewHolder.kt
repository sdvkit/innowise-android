package com.sdv.kit.taskard.viewholder

import android.view.View
import android.view.animation.AnimationUtils
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sdv.kit.taskard.R
import com.sdv.kit.taskard.contract.ScreenChanger
import com.sdv.kit.taskard.db.DatabaseClient
import com.sdv.kit.taskard.db.dao.TaskDao
import com.sdv.kit.taskard.entity.Task
import com.sdv.kit.taskard.screen.TaskInfoScreenFragment
import com.sdv.kit.taskard.util.AuthStorageUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TasksViewHolder(itemView: View) : ViewHolder(itemView) {
    private val isCompletedCheckBox: CheckBox = itemView.findViewById(R.id.isCompletedCheckBox)
    private val taskNameTextView: TextView = itemView.findViewById(R.id.taskNameTextView)
    private val isFavouriteCheckBox: CheckBox = itemView.findViewById(R.id.isFavouriteCheckBox)

    private val authStorageUtil: AuthStorageUtil by lazy {
        AuthStorageUtil(itemView.context)
    }

    private val taskDao: TaskDao by lazy {
        DatabaseClient.instance(itemView.context).taskDao()
    }

    fun bind(task: Task) {
        configureViews(task)
        setOnCheckboxesClicked(task)
        setOnItemClicked(task)
    }

    private fun setOnItemClicked(task: Task) {
        itemView.setOnClickListener {
            (itemView.context as ScreenChanger).openScreen(
                TaskInfoScreenFragment.newInstance(task))
        }
    }

    private fun configureViews(task: Task) = CoroutineScope(Dispatchers.Main).launch {
        setTaskNameColor(task)
        taskNameTextView.text = task.name
        isCompletedCheckBox.isChecked = task.isCompleted
        isFavouriteCheckBox.isChecked = task.isFavourite
    }

    private fun setOnCheckboxesClicked(task: Task) {
        isCompletedCheckBox.setOnCheckedChangeListener { _, isChecked ->
            task.isCompleted = isChecked
            updateTask(task)
            playCheckBoxAnimation()
        }

        isFavouriteCheckBox.setOnCheckedChangeListener { _, isChecked ->
            task.isFavourite = isChecked
            updateTask(task)
            setTaskNameColor(task)
        }
    }

    private fun playCheckBoxAnimation() = CoroutineScope(Dispatchers.Main).launch {
        isCompletedCheckBox.startAnimation(AnimationUtils
            .loadAnimation(itemView.context, R.anim.is_completed_checkbox_anim))
    }

    private fun setTaskNameColor(task: Task) {
        val colorId = if (task.isFavourite) R.color.primary else R.color.primary_grey
        taskNameTextView.setTextColor(ContextCompat.getColor(itemView.context, colorId))
    }

    private fun updateTask(task: Task) = CoroutineScope(Dispatchers.IO).launch {
        taskDao.update(task)
        exportTaskToFirebase(task)
    }

    private fun exportTaskToFirebase(task: Task) = CoroutineScope(Dispatchers.IO).launch {
        Firebase.database.getReference("${authStorageUtil.getUserId()}/projects/${task.projectId}/tasks/${task.id}").setValue(task)
    }
}
package com.sdv.kit.taskard.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sdv.kit.taskard.db.DatabaseClient
import com.sdv.kit.taskard.db.dao.TaskDao
import com.sdv.kit.taskard.entity.Task
import com.sdv.kit.taskard.util.AuthStorageUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskInfoViewModel(context: Context) : ViewModel() {
    private val authStorageUtil: AuthStorageUtil by lazy {
        AuthStorageUtil(context)
    }

    private val taskDao: TaskDao by lazy {
        DatabaseClient.instance(context).taskDao()
    }

    fun updateTask(task: Task) = CoroutineScope(Dispatchers.IO).launch {
        taskDao.update(task)
        exportTaskToFirebase(task)
    }

    private fun exportTaskToFirebase(task: Task) = CoroutineScope(Dispatchers.IO).launch {
        Firebase.database.getReference("${authStorageUtil.getUserId()}/projects/${task.projectId}/tasks/${task.id}").setValue(task)
    }
}
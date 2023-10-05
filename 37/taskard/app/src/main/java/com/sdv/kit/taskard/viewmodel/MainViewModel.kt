package com.sdv.kit.taskard.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.sdv.kit.taskard.db.DatabaseClient
import com.sdv.kit.taskard.db.dao.ProjectDao
import com.sdv.kit.taskard.db.dao.TaskDao
import com.sdv.kit.taskard.util.AuthStorageUtil
import com.sdv.kit.taskard.util.FirebaseDataConverterUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(context: Context) : ViewModel() {
    private val authStorageUtil: AuthStorageUtil by lazy {
        AuthStorageUtil(context)
    }

    private val projectDao: ProjectDao by lazy {
        DatabaseClient.instance(context).projectDao()
    }

    private val taskDao: TaskDao by lazy {
        DatabaseClient.instance(context).taskDao()
    }

    fun synchronizeProjectsWithFirebase() = CoroutineScope(Dispatchers.IO).launch {
        importProjectsWithTasksFromFirebase()
    }

    private fun checkIfDataIsProject(value: String): Boolean =
        value.matches("\\{name=.*, tasks=\\{.*".toRegex())

    private fun importProjectsWithTasksFromFirebase() = Firebase.database.getReference("${authStorageUtil.getUserId()}/projects").addValueEventListener(object: ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            if (snapshot.exists()) snapshot.children.forEach {
                if (checkIfDataIsProject(it.value.toString())) CoroutineScope(Dispatchers.IO).launch {
                    val projectWithTasks = FirebaseDataConverterUtil.fromFirebaseProject(it.getValue<Map<String, Any>>()!!)
                    projectDao.save(projectWithTasks.project)
                    taskDao.save(*projectWithTasks.tasks.toTypedArray())
                }
            }
        }

        override fun onCancelled(error: DatabaseError) {}
    })
}
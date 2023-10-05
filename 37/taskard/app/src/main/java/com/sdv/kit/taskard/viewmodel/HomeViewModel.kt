package com.sdv.kit.taskard.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sdv.kit.taskard.db.DatabaseClient
import com.sdv.kit.taskard.db.dao.ProjectDao
import com.sdv.kit.taskard.db.dao.TaskDao
import com.sdv.kit.taskard.entity.Project
import com.sdv.kit.taskard.entity.Task
import com.sdv.kit.taskard.entity.relation.ProjectWithTasks
import com.sdv.kit.taskard.util.AuthStorageUtil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(context: Context) : ViewModel() {
    private val authStorageUtil: AuthStorageUtil by lazy {
        AuthStorageUtil(context)
    }

    private val projectDao: ProjectDao by lazy {
        DatabaseClient.instance(context).projectDao()
    }

    private val taskDao: TaskDao by lazy {
        DatabaseClient.instance(context).taskDao()
    }

    private val _projects = MutableLiveData<List<ProjectWithTasks>>(listOf())
    val projects: LiveData<List<ProjectWithTasks>> = _projects

    private val _tasks = MutableLiveData<List<Task>>(listOf())
    val tasks: LiveData<List<Task>> = _tasks

    fun startObservingProjects() = CoroutineScope(Dispatchers.IO).launch {
        projectDao.findAllByUserIdFlowable(authStorageUtil.getUserId())
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe { changeProjectsValue(it) }
    }

    fun saveProject(projectName: String) = CoroutineScope(Dispatchers.IO).launch {
        val projectToSave = Project(null, authStorageUtil.getUserId(), projectName)
        val savedProjectId = projectDao.save(projectToSave)
        val savedProject = projectDao.findById(savedProjectId)
        saveProjectToFirebase(savedProject.project)
    }

    fun startObservingTasks() = CoroutineScope(Dispatchers.IO).launch {
        taskDao.findAllByUser(authStorageUtil.getUserId())
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe { changeTasksValue(it) }
    }

    fun updateTasksList(projectId: Long) = CoroutineScope(Dispatchers.IO).launch {
        changeTasksValue(taskDao.findAllByProjectId(projectId))
    }

    fun saveTask(task: Task) = CoroutineScope(Dispatchers.IO).launch {
        val savedTaskId = taskDao.save(task)
        val savedTask = taskDao.findById(savedTaskId)
        updateTasksList(task.projectId)
        saveTaskToFirebase(savedTask)
    }

    private fun saveTaskToFirebase(savedTask: Task) = CoroutineScope(Dispatchers.IO).launch {
        Firebase.database.getReference("${authStorageUtil.getUserId()}/projects/${savedTask.projectId}/tasks/${savedTask.id}").setValue(savedTask)
    }

    private fun saveProjectToFirebase(savedProject: Project) = CoroutineScope(Dispatchers.IO).launch {
        Firebase.database.getReference("${authStorageUtil.getUserId()}/projects/${savedProject.id}").setValue(savedProject)
    }

    private fun changeTasksValue(tasks: List<Task>) = CoroutineScope(Dispatchers.Main).launch {
        _tasks.value = tasks
    }

    private fun changeProjectsValue(projects: List<ProjectWithTasks>) = CoroutineScope(Dispatchers.Main).launch {
        _projects.value = projects
    }
}
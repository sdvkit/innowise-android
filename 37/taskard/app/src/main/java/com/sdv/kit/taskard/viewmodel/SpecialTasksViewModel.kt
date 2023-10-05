package com.sdv.kit.taskard.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.sdv.kit.taskard.db.DatabaseClient
import com.sdv.kit.taskard.db.dao.TaskDao
import com.sdv.kit.taskard.entity.Task
import com.sdv.kit.taskard.util.AuthStorageUtil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SpecialTasksViewModel(context: Context) : ViewModel() {
    private val authStorageUtil: AuthStorageUtil by lazy {
        AuthStorageUtil(context)
    }

    private val taskDao: TaskDao by lazy {
        DatabaseClient.instance(context).taskDao()
    }

    private val _tasks = MediatorLiveData<List<Task>>(listOf())
    val tasks: LiveData<List<Task>> = _tasks

    fun startObservingTasks() = CoroutineScope(Dispatchers.IO).launch {
        taskDao.findAllByUser(authStorageUtil.getUserId())
            .observeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { changeTasksValue(it) }
    }

    private fun changeTasksValue(tasks: List<Task>) = CoroutineScope(Dispatchers.Main).launch {
        _tasks.value = tasks
    }
}
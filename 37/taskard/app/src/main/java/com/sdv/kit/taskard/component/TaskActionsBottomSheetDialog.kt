package com.sdv.kit.taskard.component

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sdv.kit.taskard.R
import com.sdv.kit.taskard.contract.ScreenChanger
import com.sdv.kit.taskard.db.DatabaseClient
import com.sdv.kit.taskard.db.dao.TaskDao
import com.sdv.kit.taskard.entity.Task
import com.sdv.kit.taskard.util.AuthStorageUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskActionsBottomSheetDialog(
    private val task: Task
) : BottomSheetDialogFragment(R.layout.bottom_sheet_dialog_task_actions) {
    private lateinit var deleteButton: AppCompatButton

    private val authStorageUtil: AuthStorageUtil by lazy {
        AuthStorageUtil(requireContext())
    }

    private val taskDao: TaskDao by lazy {
        DatabaseClient.instance(requireContext()).taskDao()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
        setClickListeners()
    }

    private fun setClickListeners() {
        deleteButton.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Delete task")
                .setMessage("Are you serious about deleting this task?")
                .setPositiveButton("Delete") { _, _ -> deleteTask() }
                .setNegativeButton("Cancel") { _, _ -> dismiss() }
                .create()
                .show()
        }
    }

    private fun deleteTask() = CoroutineScope(Dispatchers.IO).launch {
        taskDao.deleteById(task.id!!)
        deleteFromFirebase()
        (requireContext() as ScreenChanger).closeCurrentScreen()
        dismiss()
    }

    private fun deleteFromFirebase() = CoroutineScope(Dispatchers.IO).launch {
        Firebase.database.getReference("${authStorageUtil.getUserId()}/projects/${task.projectId}/tasks/${task.id}").removeValue()
    }

    private fun initializeViews() {
        deleteButton = requireView().findViewById(R.id.deleteButton)
    }
}
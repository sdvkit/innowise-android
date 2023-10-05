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
import com.sdv.kit.taskard.db.dao.ProjectDao
import com.sdv.kit.taskard.entity.relation.ProjectWithTasks
import com.sdv.kit.taskard.screen.MainScreenFragment
import com.sdv.kit.taskard.util.AuthStorageUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProjectActionsBottomSheetDialog(
    private val projectWithTasks: ProjectWithTasks
) : BottomSheetDialogFragment(R.layout.bottom_sheet_dialog_project_actions) {
    private lateinit var renameButton: AppCompatButton
    private lateinit var deleteButton: AppCompatButton

    private val authStorageUtil: AuthStorageUtil by lazy {
        AuthStorageUtil(requireContext())
    }

    private val projectDao: ProjectDao by lazy {
        DatabaseClient.instance(requireContext()).projectDao()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
        setClickListeners()
    }

    private fun setClickListeners() {
        renameButton.setOnClickListener {
            RenameProjectBottomSheetDialog(projectWithTasks.project) { newProjectName ->
                updateProject(newProjectName)
            }.show(childFragmentManager, null)
        }

        deleteButton.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Delete project")
                .setMessage("Are you serious about deleting this project?")
                .setPositiveButton("Delete") { _, _ -> deleteProject() }
                .setNegativeButton("Cancel") { _, _ -> dismiss() }
                .create()
                .show()
        }
    }

    private fun updateProject(newProjectName: String) = CoroutineScope(Dispatchers.IO).launch {
        projectWithTasks.project.name = newProjectName
        projectDao.update(projectWithTasks.project)
        updateInFirebaseDatabase(newProjectName)
        closeDialog()
    }

    private fun updateInFirebaseDatabase(newProjectName: String) = CoroutineScope(Dispatchers.IO).launch {
        Firebase.database.getReference("${authStorageUtil.getUserId()}/projects/${projectWithTasks.project.id}/name").setValue(newProjectName)
    }

    private fun deleteProject() = CoroutineScope(Dispatchers.IO).launch {
        projectDao.deleteById(projectWithTasks.project.id!!)
        deleteFromFirebaseDatabase()
        closeDialog()
    }

    private fun deleteFromFirebaseDatabase() = CoroutineScope(Dispatchers.IO).launch {
        Firebase.database.getReference("${authStorageUtil.getUserId()}/projects/${projectWithTasks.project.id}").removeValue()
    }

    private fun closeDialog() {
        dismiss()
        (context as ScreenChanger).changeScreen(MainScreenFragment())
    }

    private fun initializeViews() {
        renameButton = requireView().findViewById(R.id.renameButton)
        deleteButton = requireView().findViewById(R.id.deleteButton)
    }
}
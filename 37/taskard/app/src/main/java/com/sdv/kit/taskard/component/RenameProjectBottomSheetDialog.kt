package com.sdv.kit.taskard.component

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sdv.kit.taskard.R
import com.sdv.kit.taskard.entity.Project

class RenameProjectBottomSheetDialog(
    private val project: Project,
    private val onSave: (String) -> Unit
) : BottomSheetDialogFragment(R.layout.bottom_sheet_dialog_rename_project) {
    private lateinit var newProjectNameEditText: EditText
    private lateinit var saveButton: AppCompatButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
        configureViews()
        setSaveButtonClickListener()
    }

    private fun configureViews() {
        newProjectNameEditText.text= Editable.Factory
            .getInstance()
            .newEditable(project.name)
    }
    private fun setSaveButtonClickListener() {
        saveButton.setOnClickListener {
            if (project.name != newProjectNameEditText.text.toString()) {
                onSave(newProjectNameEditText.text.toString())
            }
            dismiss()
        }
    }

    private fun initializeViews() {
        newProjectNameEditText = requireView().findViewById(R.id.newProjectNameEditText)
        saveButton = requireView().findViewById(R.id.saveButton)
    }
}
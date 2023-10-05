package com.sdv.kit.taskard.component

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sdv.kit.taskard.R

class NewTaskBottomSheetDialog(
    private val onSave: (String, String) -> Unit
) : BottomSheetDialogFragment(R.layout.bottom_sheet_dialog_new_task) {
    private lateinit var newTaskNameEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var saveButton: AppCompatButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
        setClickListeners()
    }

    @SuppressLint("SetTextI18n")
    private fun setClickListeners() {
        saveButton.setOnClickListener {
            val taskName = newTaskNameEditText.text.toString()
            val taskDescription = descriptionEditText.text.toString()
            onSave(taskName, taskDescription)
            dismiss()
        }
    }

    private fun initializeViews() {
        newTaskNameEditText = requireView().findViewById(R.id.newTaskNameEditText)
        saveButton = requireView().findViewById(R.id.saveButton)
        descriptionEditText = requireView().findViewById(R.id.descriptionEditText)
    }
}
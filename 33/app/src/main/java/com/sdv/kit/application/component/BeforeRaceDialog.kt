package com.sdv.kit.application.component

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import com.sdv.kit.application.R
import com.sdv.kit.application.entity.Race
import com.sdv.kit.application.util.RaceRunner

class BeforeRaceDialog(context: Context) : Dialog(context) {
    private lateinit var lapSizeEditText: EditText
    private lateinit var repairTimeEditText: EditText
    private lateinit var saveButton: AppCompatButton

    private val raceRunner: RaceRunner by lazy { context as RaceRunner }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_before_race)
        initializeViews()
        setSaveButtonClickListener()
    }

    private fun setSaveButtonClickListener() {
        saveButton.setOnClickListener {
            if (isFieldNotEmpty()) {
                val lapSize = lapSizeEditText.text.toString().toInt()
                val repairTime = repairTimeEditText.text.toString().toLong()

                raceRunner.runRace(Race(lapSize, repairTime))
                dismiss()
            }
        }
    }

    private fun isFieldNotEmpty() = lapSizeEditText.text.toString().toInt() > 0 &&
            repairTimeEditText.text.toString().toInt() > 0


    private fun initializeViews() {
        lapSizeEditText = findViewById(R.id.lapSizeEditText)
        repairTimeEditText = findViewById(R.id.repairTimeEditText)
        saveButton = findViewById(R.id.saveButton)
    }
}
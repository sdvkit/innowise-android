package com.sdv.kit.application

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class UpdateCounterActivity : AppCompatActivity() {
    private lateinit var updateButton: Button
    private lateinit var cancelButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_counter)
        initializeViews()
        setClickListeners()
    }

    private fun setClickListeners() {
        updateButton.setOnClickListener {
            setResult(RESULT_OK, Intent())
            finish()
        }

        cancelButton.setOnClickListener {
            setResult(RESULT_CANCELED, Intent())
            finish()
        }
    }

    private fun initializeViews() {
        updateButton = findViewById(R.id.updateButton)
        cancelButton = findViewById(R.id.cancelButton)
    }
}
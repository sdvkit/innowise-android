package com.sdv.kit.application

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity

class CounterActivity : AppCompatActivity() {
    private lateinit var compoundView: CompoundView

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter)
        initializeViews()
        initializeActivityResultLauncher()

        compoundView.tapButton.setOnClickListener {
            val intent = Intent(this, UpdateCounterActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    private fun initializeActivityResultLauncher() {
        resultLauncher = registerForActivityResult(StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                compoundView.roundCorners()
            }
        }
    }

    private fun initializeViews() {
        compoundView = findViewById(R.id.compoundView)
    }
}
package com.sdv.kit.application

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var sessionTimeLabel: TextView

    private val mainHandler = Handler(Looper.getMainLooper())
    private var sessionDuration = 0
    private var toastCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()
        launchThreads()
    }

    override fun onDestroy() {
        super.onDestroy()
        mainHandler.removeCallbacks(updateLabelRunnable)
        mainHandler.removeCallbacks(showToastRunnable)
        mainHandler.removeCallbacks(changeLabelRunnable)
    }

    private fun launchThreads() {
        mainHandler.postDelayed(updateLabelRunnable, 1_000)
        mainHandler.postDelayed(showToastRunnable, 10_000)
        mainHandler.postDelayed(changeLabelRunnable, 10_000)
    }

    private fun initializeViews() {
        sessionTimeLabel = findViewById(R.id.sessionTime)
    }

    private val updateLabelRunnable = object : Runnable {
        @SuppressLint("SetTextI18n")
        override fun run() {
            sessionTimeLabel.text = "Session time: ${++sessionDuration}s"
            mainHandler.postDelayed(this, 1_000)
        }
    }

    private val showToastRunnable = object : Runnable {
        override fun run() {
            Toast.makeText(this@MainActivity, "Session time: ${sessionDuration + 1}s", Toast.LENGTH_SHORT).show()
            toastCounter++
            mainHandler.postDelayed(this, 10_000)
        }
    }

    @SuppressLint("SetTextI18n")
    private val changeLabelRunnable = object : Runnable {
        override fun run() {
            if (toastCounter % 4 == 0) {
                sessionTimeLabel.text = "Surprise"
            }
            mainHandler.postDelayed(this, 10000)
        }
    }
}

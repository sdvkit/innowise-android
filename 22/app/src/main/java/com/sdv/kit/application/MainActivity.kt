package com.sdv.kit.application

import android.animation.ObjectAnimator
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private lateinit var progressValue: TextView
    private lateinit var randomValueButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()
        setClickListeners()
    }

    private fun animateProgress(progress: Int) {
        ObjectAnimator.ofInt(progressBar, "progress", progress)
            .setDuration(500)
            .start();
    }

    private fun setClickListeners() {
        randomValueButton.setOnClickListener {
            val generatedProgress = Random.nextInt(100) + 1
            progressValue.text = generatedProgress.toString()
            animateProgress(generatedProgress)
        }
    }

    private fun initializeViews() {
        progressBar = findViewById(R.id.progressBar)
        progressValue = findViewById(R.id.progressValue)
        randomValueButton = findViewById(R.id.randomValueButton)
    }
}
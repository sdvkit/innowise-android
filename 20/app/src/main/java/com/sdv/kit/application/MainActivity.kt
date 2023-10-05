package com.sdv.kit.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView

class MainActivity : AppCompatActivity() {
    private var mainContainerView: FragmentContainerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()
    }

    private fun initializeViews() {
        mainContainerView = findViewById(R.id.mainContainerView)
    }
}
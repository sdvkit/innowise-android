package com.sdv.kit.application

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()
        initializeNavigation()
    }

    private fun initializeNavigation() {
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.navController)
    }

    private fun initializeViews() {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.mainFragmentContainer) as NavHostFragment
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
    }
}
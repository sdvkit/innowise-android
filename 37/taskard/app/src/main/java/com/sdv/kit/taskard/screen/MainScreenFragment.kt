package com.sdv.kit.taskard.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sdv.kit.taskard.R

class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {
    private lateinit var fragmentContainerView: FragmentContainerView
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
        configureNavigation()
    }

    private fun configureNavigation() {
        val navController = (fragmentContainerView.getFragment() as NavHostFragment).navController
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }

    private fun initializeViews() {
        fragmentContainerView = requireView().findViewById(R.id.fragmentContainerView)
        bottomNavigationView = requireView().findViewById(R.id.bottomNavigationView)
    }
}
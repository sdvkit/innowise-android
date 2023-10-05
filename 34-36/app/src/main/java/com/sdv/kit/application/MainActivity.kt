package com.sdv.kit.application

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sdv.kit.application.contract.ApiConnector
import com.sdv.kit.application.contract.ScreenChanger
import com.sdv.kit.application.fragment.ConnectionScreenFragment
import com.sdv.kit.application.fragment.HistoryScreenFragment
import com.sdv.kit.application.fragment.NearbyScreenFragment
import com.sdv.kit.application.fragment.PlaceInfoScreenFragment
import com.sdv.kit.application.fragment.ProfileScreenFragment
import com.sdv.kit.application.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), ApiConnector, ScreenChanger {
    private lateinit var bottomNavigationView: BottomNavigationView

    private var viewModel: MainViewModel? = null

    override fun onDestroy() {
        super.onDestroy()
        viewModel = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        showSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureViewModel()
        observeViewModel()
        initializeViews()
        configureBottomNavigationView()
    }

    private fun configureBottomNavigationView() {
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nearby -> {
                    changeScreen(NearbyScreenFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.history -> {
                    changeScreen(HistoryScreenFragment())
                    return@setOnItemSelectedListener true
                }
                else -> {
                    changeScreen(ProfileScreenFragment())
                    return@setOnItemSelectedListener true
                }
            }
        }
    }

    private fun observeViewModel() {
        viewModel?.isAuthenticated?.observe(this) { isAuthenticated ->
            if (isAuthenticated) changeScreen(NearbyScreenFragment())
            else changeScreen(ConnectionScreenFragment())
        }
    }

    private fun initializeViews() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
    }

    private fun configureViewModel() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        lifecycle.addObserver(viewModel!!)
    }

    override fun connect() {
        viewModel?.tryConnect()
    }

    override fun changeScreen(screenFragment: Fragment): Unit = with (
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, screenFragment)
    ) {
        if (screenFragment is PlaceInfoScreenFragment) {
            addToBackStack(null)
        }

        bottomNavigationView.visibility = if (screenFragment is ConnectionScreenFragment) View.GONE else View.VISIBLE
        commit()
    }

    private fun showSplashScreen() {
        installSplashScreen()
        Thread.sleep(1_500)
    }
}
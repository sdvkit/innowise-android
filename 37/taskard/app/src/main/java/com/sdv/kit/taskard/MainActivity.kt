package com.sdv.kit.taskard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import com.sdv.kit.taskard.contract.FirebaseSynchronizer
import com.sdv.kit.taskard.contract.ScreenChanger
import com.sdv.kit.taskard.screen.AuthScreenFragment
import com.sdv.kit.taskard.screen.MainScreenFragment
import com.sdv.kit.taskard.util.AuthStorageUtil
import com.sdv.kit.taskard.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), ScreenChanger, FirebaseSynchronizer {
    private var mainViewModel: MainViewModel? = null

    private val authStorageUtil: AuthStorageUtil by lazy {
        AuthStorageUtil(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainViewModel = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureFragments()
        configureViewModel()
    }

    private fun configureViewModel() {
        mainViewModel = MainViewModel(this)
    }

    private fun configureFragments() = when {
        authStorageUtil.isUserIdSaved() -> changeScreen(MainScreenFragment())
        else -> changeScreen(AuthScreenFragment())
    }

    override fun changeScreen(screenFragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, screenFragment)
            .commit()
    }

    override fun openScreen(screenFragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerView, screenFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun closeCurrentScreen() {
        supportFragmentManager.beginTransaction()
            .remove(supportFragmentManager.fragments.last())
            .replace(R.id.fragmentContainerView,
                supportFragmentManager.fragments.first()::class.java.declaredConstructors[0].newInstance() as Fragment)
            .commit()
    }

    override fun synchronizeProjects() {
        mainViewModel?.synchronizeProjectsWithFirebase()
    }
}
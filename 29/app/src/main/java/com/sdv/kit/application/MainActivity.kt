package com.sdv.kit.application

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.sdv.kit.application.fragment.CounterFragment

class MainActivity : AppCompatActivity(), ScreenChanger, CornerRounder {
    override var currentCornersValue: Int = 0

    private lateinit var sharedPreferences: SharedPreferences
    private val counterFragment = CounterFragment()
    private var isFirstLaunch = true
    private var startPauseTime: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences(SHARED_PREF_TAG, MODE_PRIVATE)

        if (savedInstanceState == null) {
            changeScreen(CounterFragment::class.java)
        }
    }

    override fun onStart() {
        super.onStart()
        roundCorners(sharedPreferences.getInt(CURRENT_CORNERS_VALUE_TAG, 0))
    }

    override fun onResume() {
        super.onResume()

        if (isFirstLaunch) {
            isFirstLaunch = false
            return
        }

        roundCorners(2)

        val minutesInPause = (System.currentTimeMillis() - startPauseTime!!) /  (60 * 1000)
        roundCorners(-2 * minutesInPause.toInt())
    }

    override fun onPause() {
        super.onPause()
        startPauseTime = System.currentTimeMillis()
        roundCorners(5)
    }

    override fun changeScreen(fragmentClass: Class<out Fragment>) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer,
                if (CounterFragment::class.java == fragmentClass) counterFragment
                else fragmentClass.newInstance()
            )
            .commit()
    }

    override fun roundCorners(value: Int) {
        currentCornersValue += value

        sharedPreferences.edit {
            putInt(CURRENT_CORNERS_VALUE_TAG, currentCornersValue)
            commit()
        }

        counterFragment.roundCorners(currentCornersValue)
    }

    companion object {
        private const val SHARED_PREF_TAG = "compound-app-pref"
        private const val CURRENT_CORNERS_VALUE_TAG = "CurrentCornersValue"
    }
}
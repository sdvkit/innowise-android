package com.sdv.kit.taskard.contract

import androidx.fragment.app.Fragment

interface ScreenChanger {
    fun changeScreen(screenFragment: Fragment)
    fun openScreen(screenFragment: Fragment)
    fun closeCurrentScreen()
}
package com.sdv.kit.application.contract

import androidx.fragment.app.Fragment

interface ScreenChanger {
    fun changeScreen(screenFragment: Fragment)
}
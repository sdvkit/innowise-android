package com.sdv.kit.application

import androidx.fragment.app.Fragment

interface ScreenChanger {
    fun changeScreen(fragmentClass: Class<out Fragment>)
}
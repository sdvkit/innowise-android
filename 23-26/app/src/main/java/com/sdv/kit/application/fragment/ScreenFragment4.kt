package com.sdv.kit.application.fragment

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.sdv.kit.application.R

class ScreenFragment4 : Fragment(R.layout.fragment_screen_4) {
    private lateinit var linearLayout: LinearLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews(view)
        fillLayout()
    }

    private fun fillLayout() {
        for (index in 1..21) when {
            index % 4 == 1 -> {
                val view = layoutInflater.inflate(R.layout.fs_4_element_1, linearLayout, false)
                linearLayout.addView(view)
            }
            index % 2 == 0 -> {
                val view = layoutInflater.inflate(R.layout.fs_4_element_2, linearLayout, false)
                linearLayout.addView(view)
            }
            else -> {
                val view = layoutInflater.inflate(R.layout.fs_4_element_3, linearLayout, false)
                linearLayout.addView(view)
            }
        }
    }

    private fun initializeViews(root: View) {
        linearLayout = root.findViewById(R.id.linearLayout)
    }
}
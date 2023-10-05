package com.sdv.kit.application.fragment

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.GridLayout
import androidx.fragment.app.Fragment
import com.sdv.kit.application.R
import com.sdv.kit.application.entity.Card
import com.sdv.kit.application.util.CardGenerator

class ScreenFragment3 : Fragment(R.layout.fragment_screen_3) {
    private lateinit var gridLayout: GridLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews(view)

        CardGenerator.generate(requireContext()).forEach { card -> gridLayout.addView(card) }
    }

    private fun initializeViews(root: View) {
        gridLayout = root.findViewById(R.id.gridLayout)
    }
}
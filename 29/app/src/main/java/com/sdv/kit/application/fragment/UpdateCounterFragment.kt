package com.sdv.kit.application.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.sdv.kit.application.CornerRounder
import com.sdv.kit.application.R
import com.sdv.kit.application.ScreenChanger

class UpdateCounterFragment : Fragment(R.layout.fragment_update_counter) {
    private lateinit var updateButton: Button
    private lateinit var cancelButton: Button

    private var screenChanger: ScreenChanger? = null
    private var cornerRounder: CornerRounder? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews(view)
        setClickListeners()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        screenChanger = if (context is ScreenChanger) context else null
        cornerRounder = if (context is CornerRounder) context else null
    }

    override fun onDetach() {
        super.onDetach()
        screenChanger = null
        cornerRounder = null
    }

    private fun setClickListeners() {
        updateButton.setOnClickListener {
            cornerRounder?.roundCorners(10)
            screenChanger?.changeScreen(CounterFragment::class.java)
        }

        cancelButton.setOnClickListener {
            screenChanger?.changeScreen(CounterFragment::class.java)
        }
    }

    private fun initializeViews(root: View) {
        updateButton = root.findViewById(R.id.updateButton)
        cancelButton = root.findViewById(R.id.cancelButton)
    }
}
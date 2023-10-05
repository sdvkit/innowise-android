package com.sdv.kit.application.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.sdv.kit.application.CornerRounder
import com.sdv.kit.application.R
import com.sdv.kit.application.ScreenChanger
import com.sdv.kit.application.compound.CompoundView

class CounterFragment : Fragment(R.layout.fragment_counter) {
    private lateinit var compoundView: CompoundView

    private var cornerRounder: CornerRounder? = null
    private var screenChanger: ScreenChanger? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews(view)
        setClickListeners()
        roundCorners(cornerRounder?.currentCornersValue ?: 0)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        cornerRounder = if (context is CornerRounder) context else null
        screenChanger = if (context is ScreenChanger) context else null
    }

    override fun onDetach() {
        super.onDetach()
        cornerRounder = null
        screenChanger = null
    }

    private fun setClickListeners() {
        compoundView.tapButton.setOnClickListener {
            screenChanger?.changeScreen(UpdateCounterFragment::class.java)
        }
    }

    private fun initializeViews(root: View) {
        compoundView = root.findViewById(R.id.compoundView)
    }

    fun roundCorners(value: Int) {
        compoundView.roundCorners(value)
    }
}
package com.sdv.kit.application.fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sdv.kit.application.R

class MainFragment : Fragment(R.layout.fragment_main) {
    private var screenButton1: Button? = null
    private var screenButton2: Button? = null
    private var screenButton3: Button? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews(view)
        setClickListeners()
    }

    private fun setClickListeners() {
        screenButton1?.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_screenFragment1)
        }

        screenButton2?.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_screenFragment2)
        }

        screenButton3?.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_screenFragment3)
        }
    }

    private fun initializeViews(root: View) {
        screenButton1 = root.findViewById(R.id.screenButton1)
        screenButton2 = root.findViewById(R.id.screenButton2)
        screenButton3 = root.findViewById(R.id.screenButton3)
    }
}
package com.sdv.kit.application.fragment

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.sdv.kit.application.R

class ScreenFragment2 : Fragment(R.layout.fragment_screen_2) {
    private var viewsLayout: LinearLayout? = null
    private var addButton: Button? = null
    private var removeButton: Button? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews(view)
        setClickListeners()
    }

    private fun generateView(): View = View(context).apply {
        layoutParams = ViewGroup.LayoutParams(300, 300)
        setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.black))
    }

    private fun setClickListeners() {
        addButton?.setOnClickListener {
            viewsLayout?.addView(generateView())
        }

        removeButton?.setOnClickListener {
            val childCount = viewsLayout?.childCount

            if (childCount!! > 0) {
                viewsLayout?.removeViewAt(childCount - 1)
            }
        }
    }

    private fun initializeViews(root: View) {
        viewsLayout = root.findViewById(R.id.viewsLayout)
        addButton = root.findViewById(R.id.addButton)
        removeButton = root.findViewById(R.id.removeButton)
    }
}
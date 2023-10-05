package com.sdv.kit.application.fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.sdv.kit.application.R
import com.sdv.kit.application.data.RandomImageGenerator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScreenFragment1 : Fragment(R.layout.fragment_screen_1) {
    private var contentBlock: ImageView? = null
    private var showButton: Button? = null
    private var hideButton: Button? = null
    private var hiddenLabel: TextView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews(view)
        setOnClickListeners()
        loadDefaultImage()
    }

    private fun setOnClickListeners() {
        showButton?.setOnClickListener {
            contentBlock?.visibility = View.INVISIBLE
            hiddenLabel?.visibility = View.VISIBLE
        }

        hideButton?.setOnClickListener {
            contentBlock?.visibility = View.VISIBLE
            hiddenLabel?.visibility = View.INVISIBLE
        }
    }

    private fun initializeViews(root: View) {
        contentBlock = root.findViewById(R.id.contentBlock)
        showButton = root.findViewById(R.id.showButton)
        hideButton = root.findViewById(R.id.hideButton)
        hiddenLabel = root.findViewById(R.id.hiddenLabel)
    }

    private fun loadDefaultImage() = CoroutineScope(Dispatchers.Main).launch {
        Glide.with(this@ScreenFragment1)
            .load(RandomImageGenerator.getImageUrl())
            .placeholder(R.drawable.ic_launcher_background)
            .centerCrop()
            .into(contentBlock!!)
    }
}
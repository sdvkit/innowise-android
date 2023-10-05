package com.sdv.kit.application.fragment

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.sdv.kit.application.R

class ScreenFragment3 : Fragment(R.layout.fragment_screen_3) {
    private var imageView: ImageView? = null
    private var addAlpha: Button? = null
    private var removeAlpha: Button? = null

    private var alphaValue = 1.0f

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews(view)
        setClickListeners()
    }

    private fun playAnimation() {
        val animator = ObjectAnimator.ofFloat(imageView, View.ALPHA, alphaValue)
        animator.duration = 1000
        animator.start()
    }

    private fun addAlphaAnim() {
        alphaValue -= 0.1f

        if (alphaValue < 0.1f) {
            alphaValue = 0.1f
        }

        playAnimation()
    }

    private fun removeAlphaAnim() {
        alphaValue += 0.1f

        if (alphaValue > 1.0f) {
            alphaValue = 1.0f
        }

        playAnimation()
    }

    private fun setClickListeners() {
        addAlpha?.setOnClickListener { addAlphaAnim() }
        removeAlpha?.setOnClickListener { removeAlphaAnim() }
    }

    private fun initializeViews(root: View) {
        imageView = root.findViewById(R.id.imageView)
        addAlpha = root.findViewById(R.id.addAlpha)
        removeAlpha = root.findViewById(R.id.removeAlpha)
    }
}
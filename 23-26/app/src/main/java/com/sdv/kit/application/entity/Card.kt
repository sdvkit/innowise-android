package com.sdv.kit.application.entity

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.setMargins
import com.sdv.kit.application.R

class Card(context: Context): FrameLayout(context) {
    private lateinit var textView: TextView
    private lateinit var imageView: ImageView

    init {
        layoutParams = GridLayout.LayoutParams().apply {
            height = resources.getDimensionPixelSize(R.dimen.card_height)
            width = 0
            columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
            setMargins(resources.getDimensionPixelSize(R.dimen.card_margins))
        }

        addImageView()
        addTextView()

        background = ContextCompat.getDrawable(context, R.color.purple)
    }

    private fun addTextView() {
        textView = TextView(context).apply {
            val padding = resources.getDimensionPixelSize(R.dimen.card_default_padding)
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
            setTextColor(ContextCompat.getColor(context, R.color.black))
            textSize = 18F
            gravity = Gravity.BOTTOM
            setPadding(padding, padding, padding, padding)
        }

        addView(textView)
    }

    private fun addImageView() {
        imageView = ImageView(context).apply {
            val imageSize = resources.getDimensionPixelSize(R.dimen.card_image_size)
            layoutParams = LayoutParams(imageSize, imageSize)
        }

        addView(imageView)
    }

    fun applySettings(text: String, color: String, imageResId: Int): Card {
        textView.text = text
        imageView.setImageResource(imageResId)
        setBackgroundColor(Color.parseColor(color))
        return this
    }
}
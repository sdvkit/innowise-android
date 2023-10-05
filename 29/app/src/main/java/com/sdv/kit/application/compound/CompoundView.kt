package com.sdv.kit.application.compound

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.sdv.kit.application.R

class CompoundView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    private lateinit var counter: TextView
    lateinit var tapButton: Button

    init {
        inflate(context, R.layout.view_compound, this)
        initializeViews()
    }

    fun roundCorners(value: Int) {
        counter.text = value.toString()

        val background = GradientDrawable().apply {
            cornerRadius = value.toFloat()
            setColor(ContextCompat.getColor(context, R.color.yellow))
        }

        (tapButton.parent as View).background = background
    }

    private fun initializeViews() {
        tapButton = findViewById(R.id.tapButton)
        counter = findViewById(R.id.counter)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        changeButtonSize()
    }

    private fun changeButtonSize() {
        val tapButtonLayoutParams = (tapButton.layoutParams as LayoutParams).apply {
            width = this@CompoundView.measuredWidth / 3
            height = this@CompoundView.measuredWidth / 3
        }

        tapButton.layoutParams = tapButtonLayoutParams
    }
}

package com.sdv.kit.application.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.sdv.kit.application.R

class ColorSwitchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var btnPrevious: Button? = null
    private var tvValue: TextView? = null
    private var btnNext: Button? = null

    private val colors: List<Int> by lazy {
        listOf(
            R.color.black,
            R.color.white,
            R.color.red,
            R.color.green,
            R.color.blue,
            R.color.grey,
            R.color.yellow,
            R.color.purple,
            R.color.orange
        )
    }

    private var currentIndex = 0

    init {
        initializeLayout()
        initializeViews()
        setOnClickListeners()
        updateColor()
    }

    private fun initializeLayout() {
        LayoutInflater.from(context)
            .inflate(R.layout.color_switch_view, this, true)
    }

    private fun initializeViews() {
        btnPrevious = findViewById(R.id.btnPrevious)
        tvValue = findViewById(R.id.tvValue)
        btnNext = findViewById(R.id.btnNext)
    }

    private fun setOnClickListeners() {
        btnPrevious?.setOnClickListener {
            currentIndex = (currentIndex - 1 + colors.size) % colors.size
            updateColor()
        }

        btnNext?.setOnClickListener {
            currentIndex = (currentIndex + 1) % colors.size
            updateColor()
        }
    }

    @SuppressLint("SetTextI18n")
    @OptIn(ExperimentalStdlibApi::class)
    private fun updateColor() {
        val color = ContextCompat.getColor(context, colors[currentIndex])
        setBackgroundColor(color)
        tvValue?.text = "$currentIndex - #${color.toHexString(HexFormat.UpperCase)}"
    }
}
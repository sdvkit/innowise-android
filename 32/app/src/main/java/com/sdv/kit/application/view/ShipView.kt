package com.sdv.kit.application.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.sdv.kit.application.R

class ShipView(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    lateinit var shipIcon: ImageView
    lateinit var shipName: TextView
    lateinit var shipCapacity: TextView

    init {
        inflate(context, R.layout.view_ship, this)
        initializeViews()
    }

    private fun initializeViews() {
        shipIcon = findViewById(R.id.shipIcon)
        shipName = findViewById(R.id.shipName)
        shipCapacity = findViewById(R.id.shipCapacity)
    }
}
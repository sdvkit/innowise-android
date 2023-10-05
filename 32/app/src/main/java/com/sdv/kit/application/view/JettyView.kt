package com.sdv.kit.application.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.sdv.kit.application.R
import com.sdv.kit.application.async.Jetty
import com.sdv.kit.application.entity.Ship
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class JettyView(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    private lateinit var jettyImage: ImageView
    private lateinit var jettyName: TextView
    private lateinit var shipView: ShipView
    private lateinit var loadProgress: ProgressBar

    private val mainDispatcher = Dispatchers.Main

    init {
        inflate(context, R.layout.view_jetty, this)
        initializeViews()
    }

    suspend fun updateProgress() = withContext(mainDispatcher) {
        loadProgress.progress += 10
    }

    suspend fun hideShipInfo() = withContext(mainDispatcher) {
        shipView.visibility = View.GONE
    }

    @SuppressLint("SetTextI18n")
    suspend fun showShipView(ship: Ship) = withContext(mainDispatcher) {
        loadProgress.progress = 0

        shipView.apply {
            visibility = View.VISIBLE
            shipName.text = "Ship ${ship.type} #${ship.id}"
            shipCapacity.text = ship.capacity.value.toString()
            shipIcon.setImageResource(ship.type.shipImageResource)
        }

        loadProgress.max = ship.capacity.value
    }

    @SuppressLint("SetTextI18n")
    private fun setViewValues(jetty: Jetty) {
        jettyName.text = "${jetty.jettyType.typeName} jetty"
        jettyImage.setImageResource(jetty.jettyType.jettyImageResource)
    }

    private fun initializeViews() {
        jettyName = findViewById(R.id.jettyName)
        shipView = findViewById(R.id.shipView)
        jettyImage = findViewById(R.id.jettyImage)
        loadProgress = findViewById(R.id.loadProgress)
    }

    companion object {
        fun newInstance(
            context: Context,
            attrs: AttributeSet?,
            jetty: Jetty
        ): JettyView = JettyView(context, attrs).apply {
            jetty.subscribe(this)
            setViewValues(jetty)
        }
    }
}
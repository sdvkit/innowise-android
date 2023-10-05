package com.sdv.kit.application.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import com.sdv.kit.application.R
import com.sdv.kit.application.entity.Ship
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TunnelView(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private lateinit var tunnelLayout: LinearLayout

    init {
        inflate(context, R.layout.view_tunnel, this)
        initializeViews()
    }

    suspend fun takeFromTunnel() = withContext(Dispatchers.Main) {
        tunnelLayout.removeViewAt(0)
    }

    suspend fun pushShipToTunnel(ship: Ship) = withContext(Dispatchers.Main) {
        val tunnelItem = ImageView(context).apply {
            layoutParams = getTunnelItemLayoutParams()
            setImageResource(ship.type.shipImageResource)
        }

        tunnelLayout.addView(tunnelItem)
    }

    private fun getTunnelItemLayoutParams(): LayoutParams {
        val itemSize = resources.getDimensionPixelOffset(R.dimen.tunnel_item_size)
        return LayoutParams(itemSize, itemSize, 1f)
    }

    private fun initializeViews() {
        tunnelLayout = findViewById(R.id.tunnelLayout)
    }
}
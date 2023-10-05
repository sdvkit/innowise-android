package com.sdv.kit.application.async

import com.sdv.kit.application.entity.Ship
import com.sdv.kit.application.entity.ShipCapacity
import com.sdv.kit.application.entity.ShipType
import com.sdv.kit.application.view.TunnelView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.BlockingQueue

class ShipGenerator(private val tunnel: BlockingQueue<Ship>) {
    private val tunnelViewObservers = mutableListOf<TunnelView>()

    fun startGenerating() = CoroutineScope(Dispatchers.Default).launch {
        while (true) {
            delay(2_500)
            val generatedShip = generateShip()

            withContext(Dispatchers.IO) {
                tunnel.put(generatedShip)
            }

            notifyGenerated(generatedShip)
        }
    }

    private fun generateShip(): Ship {
        val randomCapacity = ShipCapacity.values().random()
        val randomShipType = ShipType.values().random()
        return Ship(currentAutoId++, randomCapacity, randomShipType)
    }

    fun subscribe(tunnelView: TunnelView) {
        tunnelViewObservers.add(tunnelView)
    }

    private suspend fun notifyGenerated(generatedShip: Ship) {
        tunnelViewObservers.forEach { it.pushShipToTunnel(generatedShip) }
    }

    companion object {
        private var currentAutoId = 1
    }
}
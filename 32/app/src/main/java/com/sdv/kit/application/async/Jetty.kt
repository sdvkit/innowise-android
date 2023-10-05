package com.sdv.kit.application.async

import com.sdv.kit.application.entity.Ship
import com.sdv.kit.application.entity.ShipType
import com.sdv.kit.application.view.JettyView
import com.sdv.kit.application.view.TunnelView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.BlockingQueue

class Jetty(
    private val tunnel: BlockingQueue<Ship>,
    val jettyType: ShipType
) {
    private val jettyViewObservers = mutableListOf<JettyView>()
    private val tunnelViewObservers = mutableListOf<TunnelView>()

    fun startLoading() = CoroutineScope(Dispatchers.IO).launch {
        while (!Thread.currentThread().isInterrupted) {
            if (!tunnel.isEmpty() && isCompatibleShip()) {
                val extractedShip = extractShip()
                notifyExtractFromTunnel()

                notifyLoadStarted(extractedShip)
                repeat(extractedShip.capacity.value / 10) { notifyUpdateProgress() }
                notifyLoadEnded()
            }
        }
    }

    fun subscribe(jettyView: JettyView) {
        jettyViewObservers.add(jettyView)
    }

    fun subscribe(tunnelView: TunnelView) {
        tunnelViewObservers.add(tunnelView)
    }

    private suspend fun notifyExtractFromTunnel() = tunnelViewObservers.forEach { obserber ->
        obserber.takeFromTunnel()
    }

    private suspend fun notifyUpdateProgress() = jettyViewObservers.forEach { observer ->
        delay(1_000)
        observer.updateProgress()
    }

    private suspend fun notifyLoadStarted(ship: Ship) = jettyViewObservers.forEach { observer ->
        observer.showShipView(ship)
    }

    private suspend fun notifyLoadEnded() = jettyViewObservers.forEach { observer ->
        observer.hideShipInfo()
    }

    private suspend fun extractShip(): Ship = withContext(Dispatchers.IO) {
        tunnel.take()
    }

    private fun isCompatibleShip(): Boolean {
        val lastShipInTunnel = tunnel.peek()
        return lastShipInTunnel != null && lastShipInTunnel.type == jettyType
    }
}
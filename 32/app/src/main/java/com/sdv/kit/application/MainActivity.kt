package com.sdv.kit.application

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.sdv.kit.application.async.Jetty
import com.sdv.kit.application.async.ShipGenerator
import com.sdv.kit.application.entity.Ship
import com.sdv.kit.application.entity.ShipType
import com.sdv.kit.application.view.JettyView
import com.sdv.kit.application.view.TunnelView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.LinkedBlockingQueue

class MainActivity : AppCompatActivity() {
    private lateinit var jettyLinearLayout: LinearLayout
    private lateinit var tunnelView: TunnelView

    private val tunnel = LinkedBlockingQueue<Ship>(5)
    private val shipGenerator = ShipGenerator(tunnel)
    private val jetties = ShipType.values().map { Jetty(tunnel, it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()
        fillJettyLinearLayout()
        setUpTunnelView()
        runTasks()
    }

    private fun setUpTunnelView() {
        shipGenerator.subscribe(tunnelView)
        jetties.forEach { it.subscribe(tunnelView) }
    }

    private fun fillJettyLinearLayout() = jetties.forEach {
        jettyLinearLayout.addView(JettyView.newInstance(this, null, it))
    }

    private fun runTasks() = CoroutineScope(Dispatchers.IO).launch {
        shipGenerator.startGenerating()
        jetties.forEach { it.startLoading() }
    }

    private fun initializeViews() {
        jettyLinearLayout = findViewById(R.id.jettyLinearLayout)
        tunnelView = findViewById(R.id.tunnelView)
    }
}
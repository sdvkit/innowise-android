package com.sdv.kit.application.entity

import android.util.Log

class Bike(
    speed: Int,
    tirePuncturePercent: Int,
    val hasSidecar: Boolean
) : Vehicle(speed, tirePuncturePercent) {
    override fun logVehicleInfo() {
        Log.i("Bike[$id]", "speed=$speed, tire puncture%=$tirePuncturePercent, has sidecar=$hasSidecar")
    }

    override fun logPassedDistance(passedDistance: Int) {
        Log.i("Bike[$id]", "Passed $passedDistance")
    }

    override fun logFinishRaceInfo() {
        Log.i("Bike[$id]", "Finished")
    }

    override fun logTirePuncture() {
        Log.i("Bike[$id]", "Tire has been punctured")
    }
}
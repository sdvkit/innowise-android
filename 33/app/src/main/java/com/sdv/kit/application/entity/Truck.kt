package com.sdv.kit.application.entity

import android.util.Log

class Truck(
    speed: Int,
    tirePuncturePercent: Int,
    val cargoWeight: Int
) : Vehicle(speed, tirePuncturePercent) {
    override fun logVehicleInfo() {
        Log.i("Truck[$id]", "speed=$speed, tire puncture%=$tirePuncturePercent, cargo weight=$cargoWeight")
    }

    override fun logPassedDistance(passedDistance: Int) {
        Log.i("Truck[$id]", "Passed $passedDistance")
    }

    override fun logFinishRaceInfo() {
        Log.i("Truck[$id]", "Finished")
    }

    override fun logTirePuncture() {
        Log.i("Truck[$id]", "Tire has been punctured")
    }
}
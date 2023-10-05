package com.sdv.kit.application.entity

import android.util.Log

class PassengerCar(
    speed: Int,
    tirePuncturePercent: Int,
    val passengersNumber: Int
) : Vehicle(speed, tirePuncturePercent) {
    override fun logVehicleInfo() {
        Log.i("Passenger car[$id]", "speed=$speed, tire puncture%=$tirePuncturePercent, number of passengers=$passengersNumber")
    }

    override fun logPassedDistance(passedDistance: Int) {
        Log.i("Passenger car[$id]", "Passed $passedDistance")
    }

    override fun logFinishRaceInfo() {
        Log.i("Passenger car[$id]", "Finished")
    }

    override fun logTirePuncture() {
        Log.i("Passenger car[$id]", "Tire has been punctured")
    }
}
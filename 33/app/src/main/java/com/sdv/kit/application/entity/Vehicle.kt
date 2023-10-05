package com.sdv.kit.application.entity

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.random.Random

abstract class Vehicle(
    val speed: Int,
    val tirePuncturePercent: Int
) {
    val id: Int = currentIdValue
    private var isTirePunctured = false

    init {
        currentIdValue++
    }

    abstract fun logVehicleInfo()
    abstract fun logPassedDistance(passedDistance: Int)
    abstract fun logFinishRaceInfo()
    abstract fun logTirePuncture()

    fun drive(race: Race) = CoroutineScope(Dispatchers.IO).async {
        repeat(race.lapSize / speed) { passedDistance ->
            delay(1_000)
            logPassedDistance(passedDistance)

            if (tryTirePuncture()) {
                repairTire(race.repairTime)
            }
        }

        finishRace(race)
    }

    private fun finishRace(race: Race) {
        race.finishers.put(this)
        logFinishRaceInfo()
    }

    private fun tryTirePuncture(): Boolean {
        val randomInt = Random.nextInt(100) + 1

        if (randomInt in 1..tirePuncturePercent) {
            isTirePunctured = true
            logTirePuncture()
        }

        return isTirePunctured
    }

    private suspend fun repairTire(repairTime: Long) {
        withContext(Dispatchers.IO) {
            Thread.sleep(repairTime)
        }
        isTirePunctured = true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vehicle

        if (speed != other.speed) return false
        if (tirePuncturePercent != other.tirePuncturePercent) return false
        if (id != other.id) return false
        if (isTirePunctured != other.isTirePunctured) return false

        return true
    }

    override fun hashCode(): Int {
        var result = speed
        result = 31 * result + tirePuncturePercent
        result = 31 * result + id
        result = 31 * result + isTirePunctured.hashCode()
        return result
    }


    companion object {
        private var currentIdValue = 1
    }
}
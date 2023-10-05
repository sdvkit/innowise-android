package com.sdv.kit.application.util

import com.sdv.kit.application.entity.Race
import com.sdv.kit.application.entity.Vehicle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RaceRunnerUtil private constructor() {
    companion object {
        fun startRace(
            race: Race,
            raceParticipants: MutableList<Vehicle>,
            onRaceEndAction: (Race) -> Unit
        ) {
            if (raceParticipants.isNotEmpty()) CoroutineScope(Dispatchers.IO).launch {
                raceParticipants.map { participant ->
                    participant.drive(race)
                }.awaitAll()

                withContext(Dispatchers.Main) {
                    onRaceEndAction(race)
                }
            }
        }
    }
}
package com.sdv.kit.application.entity

import java.util.concurrent.LinkedBlockingQueue

class Race(
    val lapSize: Int,
    val repairTime: Long
) {
    val finishers = LinkedBlockingQueue<Vehicle>()
}
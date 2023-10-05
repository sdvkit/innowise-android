package factory

import model.*
import model.AirTransport.AirTransportType
import model.WaterTransport.*
import java.lang.RuntimeException

class TransportFactory {
    fun createTransport(country: Country): Transport = when (country) {
            Country.RUSSIA -> {
                print("Enter Track: ")
                val track = readln().toDouble()
                print("Has ballast (y/n): ")
                val hasBallast = readln() == "y"
                RailwayTransport(track, hasBallast)
            }
            Country.USA -> {
                println("Select type: \n1 - Sea\n2 - River")
                val type = if (readln().toInt() == 1) WaterTransportType.SEA else WaterTransportType.RIVER
                WaterTransport(type)
            }
            Country.BELARUS -> RoadTransport()
            Country.GERMANY -> {
                println("Select type: \n1 - Intracity\n2 - International\n3 - Intercity")
                val type = when (readln().toInt()) {
                    1 -> AirTransportType.INTRACITY
                    2 -> AirTransportType.INTERNATIONAL
                    3 -> AirTransportType.INTERCITY
                    else -> throw RuntimeException("Invalid AirTransportType")
                }
                print("Is Passenger (y/n): ")
                val isPassenger = readln() == "y"
                AirTransport(type, isPassenger)
            }
    }
}
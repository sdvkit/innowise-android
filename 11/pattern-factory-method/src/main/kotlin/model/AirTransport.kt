package model

class AirTransport(private val type: AirTransportType, private val isPassenger: Boolean) : Transport() {
    override val companyName: String = "Air Transport Company"
    override val maxPayload: Double = 2000.0
    override val maxDimensions: String = "50x50x50"

    enum class AirTransportType {
        INTRACITY, INTERNATIONAL, INTERCITY
    }

    override fun toString() = "AirTransport(type=$type, isPassenger=$isPassenger, companyName='$companyName', maxPayload=$maxPayload, maxDimensions='$maxDimensions')"
}
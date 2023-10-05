package model

class WaterTransport(private val type: WaterTransportType) : Transport() {
    override val companyName: String = "Water Transport Company"
    override val maxPayload: Double = 10000.0
    override val maxDimensions: String = "100x100x100"

    enum class WaterTransportType(val value: String) {
        SEA("Sea type"),
        RIVER("River type")
    }

    override fun toString() = "WaterTransport(type=$type, companyName='$companyName', maxPayload=$maxPayload, maxDimensions='$maxDimensions')"
}
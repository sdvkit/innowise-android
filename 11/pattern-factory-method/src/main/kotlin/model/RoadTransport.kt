package model

class RoadTransport : Transport() {
    override val companyName: String = "Road Transport Company"
    override val maxPayload: Double = 3000.0
    override val maxDimensions: String = "70x70x70"

    override fun toString() = "RoadTransport(companyName='$companyName', maxPayload=$maxPayload, maxDimensions='$maxDimensions')"
}
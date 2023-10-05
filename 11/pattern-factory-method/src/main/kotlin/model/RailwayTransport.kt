package model

class RailwayTransport(private val track: Double, private val hasBallast: Boolean) : Transport() {
    override val companyName: String = "Railway Transport Company"
    override val maxPayload: Double = 5000.0
    override val maxDimensions: String = "80x80x80"

    override fun toString() = "RailwayTransport(track=$track, hasBallast=$hasBallast, companyName='$companyName', maxPayload=$maxPayload, maxDimensions='$maxDimensions')"
}
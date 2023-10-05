package model

abstract class Transport {
    abstract val companyName: String
    abstract val maxPayload: Double
    abstract val maxDimensions: String

    override fun toString() = "Transport(companyName='$companyName', maxPayload=$maxPayload, maxDimensions='$maxDimensions')"
}
import factory.TransportFactory
import model.Country

fun main() {
    val transportFactory = TransportFactory()
    val transport = transportFactory.createTransport(Country.BELARUS)
    println(transport)
}

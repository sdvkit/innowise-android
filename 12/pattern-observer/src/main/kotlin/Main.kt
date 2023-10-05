import java.util.Timer
import java.util.TimerTask
import java.util.Date

interface Observer {
    fun update(weatherData: Int)
}

class WeatherObserver private constructor() : Observer {
    private val observers: MutableList<Observer> = mutableListOf()
    private var weatherData: Int = 0

    init {
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val newWeatherData = (Date().time / 1000 % 35).toInt()
                if (newWeatherData != weatherData) {
                    weatherData = newWeatherData
                    notifyObservers()
                }
            }
        }, 0, 5000)
    }

    fun registerObserver(observer: Observer) {
        observers.add(observer)
    }

    fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }

    fun notifyObservers() {
        observers.forEach { observer -> observer.update(weatherData) }
    }

    override fun update(weatherData: Int) {
        println("Получены новые данные о погоде: $weatherData")
    }

    companion object {
        val instance: WeatherObserver by lazy { WeatherObserver() }
    }
}

class Screen(private val name: String) : Observer {
    private val weatherObserver: WeatherObserver = WeatherObserver.instance

    init {
        weatherObserver.registerObserver(this)
    }

    fun open() {
        println("Экран $name открыт")
    }

    fun close() {
        weatherObserver.removeObserver(this)
        println("Экран $name закрыт")
    }

    override fun update(weatherData: Int) {
        println("Экран $name получил новые данные о погоде: $weatherData")
    }
}

fun main() {
    val screen1 = Screen("Первый экран")
    val screen2 = Screen("Второй экран")
    val screen3 = Screen("Третий экран")

    screen1.open()
    screen2.open()
    screen3.open()

    screen1.close()
    screen2.close()
    screen3.close()
}
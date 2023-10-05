package log

class Log private constructor() {

    companion object {
        fun info(message: String) = println("LOG::$message")
    }
}
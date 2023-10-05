package model

class KotlinSun private constructor() {
    companion object Singleton {
        val instance: KotlinSun by lazy { KotlinSun() }
    }
}
import model.KotlinSun

fun main() {
    val kotlinSunVariable1 = KotlinSun.instance
    val kotlinSunVariable2 = KotlinSun.instance

    println(kotlinSunVariable1 === kotlinSunVariable2)
}
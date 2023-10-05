import model.Coffee

fun main() {
    val coffee = Coffee.builder()
        .isDoubleCoffee(false)
        .hasCream(true)
        .hasCinnamon(true)
        .spoonOfSugarCount(2)
        .hasMilk(false)
        .hasSyrup(false)
        .build()

    println(coffee)
}
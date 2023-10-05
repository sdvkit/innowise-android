package builder

import model.Coffee

class Builder {

    private var isDoubleCoffee: Boolean = false
    private var hasMilk: Boolean = false
    private var hasCream: Boolean = false
    private var spoonOfSugarCount: Int = 0
    private var hasCinnamon: Boolean = false
    private var hasSyrup: Boolean = false

    fun isDoubleCoffee(isDoubleCoffee: Boolean) = apply { this.isDoubleCoffee = isDoubleCoffee }
    fun hasMilk(hasMilk: Boolean) = apply { this.hasMilk = hasMilk }
    fun hasCream(hasCream: Boolean) = apply { this.hasCream = hasCream }
    fun spoonOfSugarCount(spoonOfSugarCount: Int) = apply { this.spoonOfSugarCount = spoonOfSugarCount }
    fun hasCinnamon(hasCinnamon: Boolean) = apply { this.hasCinnamon = hasCinnamon }
    fun hasSyrup(hasSyrup: Boolean) = apply { this.hasSyrup = hasSyrup }

    fun build() = Coffee(isDoubleCoffee, hasMilk, hasCream, spoonOfSugarCount, hasCinnamon, hasSyrup)
}
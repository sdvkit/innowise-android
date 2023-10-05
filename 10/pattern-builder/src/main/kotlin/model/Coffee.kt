package model

import builder.Builder

data class Coffee(
    val isDoubleCoffee: Boolean,
    val hasMilk: Boolean,
    val hasCream: Boolean,
    val spoonOfSugarCount: Int,
    val hasCinnamon: Boolean,
    val hasSyrup: Boolean
) {
    companion object {
        fun builder() = Builder()
    }
}
package com.sdv.kit.application.entity

import com.sdv.kit.application.R

enum class ShipType(
    val typeName: String,
    val shipImageResource: Int,
    val jettyImageResource: Int
) {
    BREAD("Bread", R.drawable.bread_ship, R.drawable.bread_jetty),
    BANANA("Banana", R.drawable.banana_ship, R.drawable.banana_jetty),
    CLOTHES("Clothes", R.drawable.clothes_ship, R.drawable.clothes_jetty);
}
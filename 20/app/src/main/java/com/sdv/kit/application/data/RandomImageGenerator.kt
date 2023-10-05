package com.sdv.kit.application.data

import kotlin.random.Random

class RandomImageGenerator private constructor() {
    companion object {
        private val images = listOf(
            "https://www.solarsystemscope.com/spacepedia/images/handbook/renders/mars.png",
            "https://www.solarsystemscope.com/spacepedia/images/handbook/renders/earth.png",
            "https://www.solarsystemscope.com/spacepedia/images/handbook/renders/jupiter.png"
        )

        fun getImageUrl(): String {
            val randomIndex = Random.nextInt(images.size)
            return images[randomIndex]
        }
    }
}
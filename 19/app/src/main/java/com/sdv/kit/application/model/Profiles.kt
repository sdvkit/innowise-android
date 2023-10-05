package com.sdv.kit.application.model

import com.sdv.kit.application.R
import com.sdv.kit.application.entity.Profile

class Profiles private constructor() {
    companion object {
        private var currentIndex = 0
        private val profiles: List<Profile> = listOf(
            Profile("Header 1", "Name 1", 1, 11, 200, 300, 400, R.color.black),
            Profile("Header 2", "Name 2", 2, 12, 200, 300, 400, R.color.green),
            Profile("Header 3", "Name 3", 3, 13, 200, 300, 400, R.color.red),
            Profile("Header 4", "Name 4", 4, 14, 200, 400, 400, R.color.grey),
            Profile("Header 5", "Name 5", 5, 15, 200, 500, 400, R.color.purple),
            Profile("Header 6", "Name 6", 6, 16, 200, 600, 400, R.color.yellow),
            Profile("Header 7", "Name 7", 7, 17, 200, 700, 400, R.color.orange),
            Profile("Header 8", "Name 8", 8, 18, 200, 800, 400, R.color.dark_blue),
            Profile("Header 9", "Name 9", 9, 19, 200, 900, 400, R.color.dark_purple),
            Profile("Header 10", "Name 10", 10, 23, 200, 1000, 400, R.color.mint),
        )

        fun next(): Profile {
            if (currentIndex == profiles.size) {
                currentIndex = 0
            }

            return profiles[currentIndex++]
        }
    }
}
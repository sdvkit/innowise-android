package com.sdv.kit.application.util

import com.sdv.kit.application.entity.Sex
import com.sdv.kit.application.entity.User
import kotlin.random.Random

class RandomUserGenerator private constructor() {
    companion object {
        private val maleFirstNames: List<String> by lazy {
            listOf("Oleg", "Ivan", "Petr", "Nikita", "Alex", "Vasya", "Kostya", "Nikolay", "Slava")
        }

        private val femaleFirstNames: List<String> by lazy {
            listOf("Olga", "Anna", "Maria", "Elena", "Natalia", "Svetlana", "Irina", "Yulia", "Ekaterina")
        }

        private val lastNames: List<String> by lazy {
            listOf("Shakova", "Volodina", "Iavnov", "Petrov", "Orlova", "Sokolov", "Zub", "Lisitsa", "Krot", "Kuznecov", "Smolskiy", "Kudelitch")
        }

        private val descriptionValues: List<String> by lazy {
            listOf(
                "awesome", "creative", "energetic", "friendly", "helpful",
                "intelligent", "kind", "loyal", "motivated", "organized",
                "passionate", "reliable", "sociable", "talented", "versatile",
                "witty", "youthful", "zealous", "adventurous", "brave"
            )
        }
        private fun generate(): User {
            val randomSex = Sex.entries.random()
            val randomAge = Random.nextInt(40) + 18
            val randomFirstName: String
            val randomLastName: String

            when (randomSex) {
                Sex.MALE -> {
                    randomFirstName = maleFirstNames.random()
                    randomLastName = lastNames.filter { lastName -> !lastName.endsWith("ova") }.random()
                }
                Sex.FEMALE -> {
                    randomFirstName = femaleFirstNames.random()
                    randomLastName = lastNames.filter { lastName -> !lastName.endsWith("ov") }.random()
                }
            }

            val randomDescriptionLinesCount = Random.nextInt(4) + 1
            val description = MutableList(randomDescriptionLinesCount) { descriptionValues.random() }

            return User(randomFirstName, randomLastName, randomAge, randomSex, description)
        }

        fun generate(count: Int): List<User> = List(count) {
            generate()
        }
    }
}

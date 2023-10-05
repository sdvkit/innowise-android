package com.sdv.kit.application.util

import android.content.Context
import com.sdv.kit.application.R
import com.sdv.kit.application.entity.Card

class CardGenerator private constructor() {
    companion object {
        private val texts: List<String> by lazy {
            listOf(
                "MY DOCTOR", "MY CAREMANAGER", "MY DIAGNOSIS",
                "THERAPY PLAN", "REMAINING PILLS", "MY ORDERS"
            )
        }

        private val colors: List<String> by lazy {
            listOf(
                "#F1F1F6", "#75DAE9", "#2AC6DD",
                "#00294B", "#F1F1F6", "#F18050"
            )
        }

        private val images: List<Int> by lazy {
            listOf(
                R.drawable.doctor, R.drawable.caremanager, R.drawable.diagnosis,
                R.drawable.plan, R.drawable.pills, R.drawable.orders
            )
        }

        private fun generate(context: Context, index: Int): Card {
            val textIndex = index % texts.size
            val colorIndex = index % colors.size
            val imageIndex = index % colors.size

            return Card(context).applySettings(
                texts[textIndex], colors[colorIndex], images[imageIndex])
        }

        fun generate(context: Context): List<Card> = MutableList(20) { generate(context, it) }
    }
}
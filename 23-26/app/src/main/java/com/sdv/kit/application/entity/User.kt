package com.sdv.kit.application.entity

data class User(
    val firstName: String,
    val lastName: String,
    val age: Int,
    val sex: Sex,
    val description: MutableList<String>
) {
    val squareAvatarUrl: String
        get() = "https://image.cnbcfm.com/api/v1/image/105773423-1551716977818rtx6p9yw.jpg?v=1551717428&w=700&h=700"
}
package com.sdv.kit.application.entity

import com.sdv.kit.application.R

data class Profile(
    val profileHeader: String,
    val profileName: String,
    val days: Int,
    val recommendationsCount: Int,
    val viewsCount: Int,
    val commentsCount: Int,
    val likesCount: Int,
    val profilePrimaryColor: Int
)
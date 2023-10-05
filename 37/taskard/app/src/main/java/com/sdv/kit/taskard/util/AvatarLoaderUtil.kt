package com.sdv.kit.taskard.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.sdv.kit.taskard.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AvatarLoaderUtil(context: Context) {
    private val authStorageUtil: AuthStorageUtil by lazy {
        AuthStorageUtil(context)
    }

    fun loadUserAvatar(avatarImageView: ImageView) = CoroutineScope(Dispatchers.IO).launch {
        when(val userAvatarUrl = authStorageUtil.getSavedUser()?.avatarUrl) {
            null -> withContext(Dispatchers.Main) {
                avatarImageView.setImageResource(R.drawable.icon_profile)
            }
            else -> loadGoogleAvatar(avatarImageView, userAvatarUrl)
        }
    }

    private suspend fun loadGoogleAvatar(
        avatarImageView: ImageView,
        userAvatarUrl: String
    ) = withContext(Dispatchers.Main) {
        Glide.with(avatarImageView)
            .load(userAvatarUrl)
            .circleCrop()
            .into(avatarImageView)
    }
}
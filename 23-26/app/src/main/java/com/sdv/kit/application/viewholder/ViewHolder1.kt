package com.sdv.kit.application.viewholder

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sdv.kit.application.R
import com.sdv.kit.application.entity.User

class ViewHolder1(private val root: View) : RecyclerView.ViewHolder(root) {
    private val avatar: ImageView = root.findViewById(R.id.avatar)
    private val firstLine: TextView = root.findViewById(R.id.firstLine)
    private val secondLine: TextView = root.findViewById(R.id.secondLine)
    private val sex: ImageView = root.findViewById(R.id.sex)

    @SuppressLint("SetTextI18n")
    fun bind(user: User) {
        setAvatar(user)
        firstLine.text = "${user.firstName} ${user.lastName}"
        secondLine.text = "Age: ${user.age}"
        sex.setImageResource(user.sex.resource)
    }

    private fun setAvatar(user: User) {
        Glide.with(root.context)
            .load(user.squareAvatarUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .circleCrop()
            .into(avatar)
    }
}
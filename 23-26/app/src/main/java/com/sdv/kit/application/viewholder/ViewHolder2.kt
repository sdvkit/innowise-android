package com.sdv.kit.application.viewholder

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sdv.kit.application.R
import com.sdv.kit.application.entity.User

class ViewHolder2(private val root: View) : RecyclerView.ViewHolder(root) {
    private val avatar: ImageView = root.findViewById(R.id.avatar)
    private val firstLine: TextView = root.findViewById(R.id.firstLine)
    private val secondLine: TextView = root.findViewById(R.id.secondLine)
    private val thirdLine: TextView = root.findViewById(R.id.thirdLine)
    private val sex: ImageView = root.findViewById(R.id.sex)

    @SuppressLint("SetTextI18n")
    fun bind(user: User) {
        firstLine.text = "${user.firstName} ${user.lastName}"
        secondLine.text = "Age: ${user.age}"
        thirdLine.text = buildDescription(user)

        setAvatar(user)
        sex.setImageResource(user.sex.resource)
    }

    private fun buildDescription(user: User): String = StringBuilder().apply {
           user.description.forEach { word -> append(word)
               if (user.description.indexOf(word) != user.description.size - 1) {
                   append(", ")
               }
           }
    }.toString()

    private fun setAvatar(user: User) {
        Glide.with(root.context)
            .load(user.squareAvatarUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .circleCrop()
            .into(avatar)
    }
}
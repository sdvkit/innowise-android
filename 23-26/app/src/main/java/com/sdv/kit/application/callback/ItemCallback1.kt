package com.sdv.kit.application.callback

import androidx.recyclerview.widget.DiffUtil
import com.sdv.kit.application.entity.User

class ItemCallback1 : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
        oldItem.firstName == newItem.firstName &&
        oldItem.lastName == newItem.lastName &&
        oldItem.age == newItem.age &&
        oldItem.sex == newItem.sex &&
        oldItem.description.size == newItem.description.size

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
        oldItem == newItem
}
package com.sdv.kit.application.callback

import androidx.recyclerview.widget.DiffUtil
import com.sdv.kit.application.api.entity.Place

class PlacesItemCallback : DiffUtil.ItemCallback<Place>() {
    override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean =
        oldItem == newItem
}
package com.sdv.kit.application.callback

import androidx.recyclerview.widget.DiffUtil
import com.sdv.kit.application.entity.Vehicle

class VehicleRecyclerViewCallback : DiffUtil.ItemCallback<Vehicle>() {
    override fun areItemsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean =
        oldItem == newItem
}
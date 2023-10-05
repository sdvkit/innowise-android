package com.sdv.kit.application.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.sdv.kit.application.R
import com.sdv.kit.application.util.VehicleRemover
import com.sdv.kit.application.callback.VehicleRecyclerViewCallback
import com.sdv.kit.application.entity.Vehicle
import com.sdv.kit.application.viewholder.VehicleViewHolder

class VehicleRecyclerViewAdapter(
    private val context: Context
) : ListAdapter<Vehicle, VehicleViewHolder>(VehicleRecyclerViewCallback()) {
    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val vehicleView = inflater.inflate(R.layout.view_vehicle_item, parent, false)
        return VehicleViewHolder(vehicleView, context as VehicleRemover)
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) =
        holder.bind(getItem(position))
}
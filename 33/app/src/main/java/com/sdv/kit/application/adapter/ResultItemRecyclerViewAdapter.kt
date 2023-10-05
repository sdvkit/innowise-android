package com.sdv.kit.application.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.sdv.kit.application.R
import com.sdv.kit.application.callback.VehicleRecyclerViewCallback
import com.sdv.kit.application.entity.Vehicle
import com.sdv.kit.application.viewholder.ResultItemViewHolder

class ResultItemRecyclerViewAdapter(context: Context) : ListAdapter<Vehicle, ResultItemViewHolder>(VehicleRecyclerViewCallback()) {
    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultItemViewHolder {
        val resultItemView = inflater.inflate(R.layout.view_results_item, parent, false)
        return ResultItemViewHolder(resultItemView)
    }

    override fun onBindViewHolder(holder: ResultItemViewHolder, position: Int) =
        holder.bind(getItem(position), position)
}
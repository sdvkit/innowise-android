package com.sdv.kit.application.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.sdv.kit.application.R
import com.sdv.kit.application.api.entity.Place
import com.sdv.kit.application.callback.PlacesItemCallback
import com.sdv.kit.application.viewholder.PlacesViewHolder

class PlacesAdapter(private val context: Context) : ListAdapter<Place, PlacesViewHolder>(PlacesItemCallback()) {
    private val inflater: LayoutInflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesViewHolder =
        PlacesViewHolder(context, inflater.inflate(R.layout.view_place_item, parent, false))

    override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) =
        holder.bind(getItem(position))
}
package com.sdv.kit.application.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sdv.kit.application.R
import com.sdv.kit.application.api.entity.Place
import com.sdv.kit.application.viewholder.PlaceImagesViewHolder

class PlaceImagesAdapter(
    private val placeImages: List<Place.Image>,
    context: Context
) : RecyclerView.Adapter<PlaceImagesViewHolder>() {
    private val inflater: LayoutInflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceImagesViewHolder =
        PlaceImagesViewHolder(inflater.inflate(R.layout.view_place_image_item, parent, false))

    override fun getItemCount(): Int = placeImages.size

    override fun onBindViewHolder(holder: PlaceImagesViewHolder, position: Int) {
        holder.bind(placeImages[position].imageUri)
    }
}
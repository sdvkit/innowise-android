package com.sdv.kit.application.viewholder

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.sdv.kit.application.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlaceImagesViewHolder(itemView: View) : ViewHolder(itemView) {
    private val placeImageView: ImageView = itemView.findViewById(R.id.placeImageView)

    fun bind(imageUri: Uri) {
        setImage(imageUri)
    }

    private fun setImage(imageUri: Uri) = CoroutineScope(Dispatchers.Main).launch {
        try {
            Glide.with(itemView)
                .load(imageUri)
                .placeholder(R.drawable.cross)
                .centerCrop()
                .into(placeImageView)
        } catch (_: Exception) {}
    }
}
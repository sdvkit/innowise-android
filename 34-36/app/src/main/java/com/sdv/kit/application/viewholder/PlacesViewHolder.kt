package com.sdv.kit.application.viewholder

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.sdv.kit.application.R
import com.sdv.kit.application.api.ApiClient
import com.sdv.kit.application.api.entity.Place
import com.sdv.kit.application.contract.ScreenChanger
import com.sdv.kit.application.fragment.PlaceInfoScreenFragment
import com.sdv.kit.application.util.SharedPrefUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlacesViewHolder(context: Context, itemView: View) : ViewHolder(itemView) {
    private val placeIcon: ImageView = itemView.findViewById(R.id.placeIcon)
    private val placeNameTextView: TextView = itemView.findViewById(R.id.placeNameTextView)
    private val placeCategoryTextView: TextView = itemView.findViewById(R.id.placeCategoryTextView)

    private val screenChanger: ScreenChanger by lazy {
        context as ScreenChanger
    }
    private val sharedPrefUtil: SharedPrefUtil by lazy {
        SharedPrefUtil(context)
    }

    fun bind(place: Place) {
        initializeViews(place)
        setItemClickListener(place)
    }

    private fun setItemClickListener(place: Place) {
        itemView.setOnClickListener {
            sharedPrefUtil.incrementFsqIdCount(place.fsqId)
            screenChanger.changeScreen(PlaceInfoScreenFragment.newInstance(place))
        }
    }

    private fun initializeViews(place: Place) {
        setPlaceIcon(place)
        placeNameTextView.text = place.name
        placeCategoryTextView.text = place.categories[0].name
    }

    private fun setPlaceIcon(place: Place) = CoroutineScope(Dispatchers.Main).launch {
        try {
            val placeImage = ApiClient.placeService.getPlaceImages(place.fsqId, 1)[0]

            Glide.with(itemView)
                .load(placeImage.imageUri)
                .placeholder(R.drawable.cross)
                .centerCrop()
                .into(placeIcon)
        } catch (_: Exception) {}
    }
}
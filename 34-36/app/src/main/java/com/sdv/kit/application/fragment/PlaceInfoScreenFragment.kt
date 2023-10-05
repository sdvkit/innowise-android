package com.sdv.kit.application.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.sdv.kit.application.R
import com.sdv.kit.application.adapter.PlaceImagesAdapter
import com.sdv.kit.application.api.ApiClient
import com.sdv.kit.application.api.entity.Place
import com.sdv.kit.application.util.NetworkUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlaceInfoScreenFragment : Fragment(R.layout.fragment_place_info) {
    private lateinit var backButton: AppCompatButton
    private lateinit var placeImagesViewPager: ViewPager2
    private lateinit var placeCategoryNameTextView: TextView
    private lateinit var placeCategoryIdTextView: TextView
    private lateinit var placeLocationTextView: TextView
    private lateinit var placeNameTextView: TextView
    private lateinit var placeFsqIdTextView: TextView

    private val networkUtil: NetworkUtil by lazy {
        NetworkUtil(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
        configureViews()
        setBackButtonClickListener()
    }

    private fun setBackButtonClickListener() {
        backButton.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
    }

    private fun configureViews() {
        setPlaceImages()
        placeNameTextView.text = requireArguments().getString(PLACE_NAME_KEY)
        placeFsqIdTextView.text = requireArguments().getString(PLACE_FSQ_ID_KEY)
        placeLocationTextView.text = requireArguments().getString(PLACE_LOCATION_KEY)
        placeCategoryIdTextView.text = requireArguments().getInt(PLACE_CATEGORY_ID_KEY).toString()
        placeCategoryNameTextView.text = requireArguments().getString(PLACE_CATEGORY_NAME_KEY)
    }

    private fun setPlaceImages() = CoroutineScope(Dispatchers.IO).launch {
        var placeImages: List<Place.Image> = listOf()

        if (networkUtil.isInternetConnectionPresents()) {
            val fsqId = requireArguments().getString(PLACE_FSQ_ID_KEY)
            placeImages = ApiClient.placeService.getPlaceImages(fsqId!!, 30)
        }

        withContext(Dispatchers.Main) {
            placeImagesViewPager.adapter = PlaceImagesAdapter(placeImages, requireContext())
        }
    }

    private fun initializeViews() {
        backButton = requireView().findViewById(R.id.backButton)
        placeImagesViewPager = requireView().findViewById(R.id.placeImagesViewPager)
        placeCategoryNameTextView = requireView().findViewById(R.id.placeCategoryNameTextView)
        placeCategoryIdTextView = requireView().findViewById(R.id.placeCategoryIdTextView)
        placeLocationTextView = requireView().findViewById(R.id.placeLocationTextView)
        placeNameTextView = requireView().findViewById(R.id.placeNameTextView)
        placeFsqIdTextView = requireView().findViewById(R.id.placeFsqIdTextView)
    }

    companion object {
        private const val PLACE_FSQ_ID_KEY = "placeFsqId"
        private const val PLACE_NAME_KEY = "placeName"
        private const val PLACE_LOCATION_KEY = "placeLocation"
        private const val PLACE_CATEGORY_ID_KEY = "placeCategoryId"
        private const val PLACE_CATEGORY_NAME_KEY = "placeCategoryName"

        fun newInstance(place: Place): PlaceInfoScreenFragment {
            val placeInfoScreenFragment = PlaceInfoScreenFragment()
            placeInfoScreenFragment.arguments = Bundle().apply {
                putString(PLACE_FSQ_ID_KEY, place.fsqId)
                putString(PLACE_NAME_KEY, place.name)
                putString(PLACE_LOCATION_KEY, place.location.formattedAddress)
                putInt(PLACE_CATEGORY_ID_KEY, place.categories[0].id)
                putString(PLACE_CATEGORY_NAME_KEY, place.categories[0].name)
            }
            return placeInfoScreenFragment
        }
    }
}
package com.sdv.kit.application.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sdv.kit.application.R
import com.sdv.kit.application.adapter.PlacesAdapter
import com.sdv.kit.application.contract.ScreenChanger
import com.sdv.kit.application.viewmodel.NearbyViewModel

class NearbyScreenFragment : Fragment(R.layout.fragment_nearby_screen) {
    private lateinit var logoutButton: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var placesLoadingProgressBar: ProgressBar

    private var viewModel: NearbyViewModel? = null
    private var screenChanger: ScreenChanger? = null
    private val placesAdapter: PlacesAdapter by lazy {
        PlacesAdapter(requireContext())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        screenChanger = context as ScreenChanger
    }

    override fun onDetach() {
        super.onDetach()
        viewModel = null
        screenChanger = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
        configureRecyclerView()
        configureViewModel()
        setLogoutClickListener()
    }

    private fun setLogoutClickListener() {
        logoutButton.setOnClickListener {
            viewModel!!.logout()
            screenChanger!!.changeScreen(ConnectionScreenFragment())
        }
    }

    private fun initializeViews() {
        logoutButton = requireView().findViewById(R.id.logoutButton)
        recyclerView = requireView().findViewById(R.id.recyclerView)
        placesLoadingProgressBar = requireView().findViewById(R.id.placesLoadingProgressBar)
    }

    private fun configureViewModel() {
        viewModel = NearbyViewModel(requireContext())

        viewModel!!.places.observe(viewLifecycleOwner) { places ->
            placesAdapter.submitList(places)
        }

        viewModel!!.isPlacesLoading.observe(viewLifecycleOwner) { isLoading ->
            placesLoadingProgressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel!!.loadPlaces()
    }

    private fun configureRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        recyclerView.adapter = placesAdapter
    }
}
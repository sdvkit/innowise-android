package com.sdv.kit.application.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.sdv.kit.application.R
import com.sdv.kit.application.api.util.TokenStorage
import com.sdv.kit.application.contract.ScreenChanger
import com.sdv.kit.application.viewmodel.ProfileViewModel

class ProfileScreenFragment : Fragment(R.layout.fragment_profile_screen) {
    private lateinit var accessTokenTextView: TextView
    private lateinit var logoutButton: TextView

    private var viewModel: ProfileViewModel? = null
    private var screenChanger: ScreenChanger? = null

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
        configureViews()
        configureViewModel()
        setLogoutClickListener()
    }

    private fun configureViewModel() {
        viewModel = ProfileViewModel(requireContext())
    }

    private fun setLogoutClickListener() {
        logoutButton.setOnClickListener {
            viewModel!!.logout()
            screenChanger!!.changeScreen(ConnectionScreenFragment())
        }
    }

    private fun configureViews() {
        accessTokenTextView.text = TokenStorage(requireContext()).getAccessToken()
    }

    private fun initializeViews() {
        accessTokenTextView = requireView().findViewById(R.id.accessTokenTextView)
        logoutButton = requireView().findViewById(R.id.logoutButton)
    }
}
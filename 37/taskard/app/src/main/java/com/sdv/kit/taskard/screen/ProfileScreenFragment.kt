package com.sdv.kit.taskard.screen

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.sdv.kit.taskard.R
import com.sdv.kit.taskard.contract.ScreenChanger
import com.sdv.kit.taskard.util.AuthStorageUtil
import com.sdv.kit.taskard.util.AvatarLoaderUtil
import com.sdv.kit.taskard.viewmodel.ProfileViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileScreenFragment : Fragment(R.layout.fragment_profile_screen) {
    private lateinit var avatarImageView: ImageView
    private lateinit var usernameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var logoutButton: AppCompatButton

    private var screenChanger: ScreenChanger? = null
    private var profileViewModel: ProfileViewModel? = null
    private var authStorageUtil: AuthStorageUtil? = null
    private var avatarLoaderUtil: AvatarLoaderUtil? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        screenChanger = context as ScreenChanger
    }

    override fun onDetach() {
        super.onDetach()
        profileViewModel = null
        authStorageUtil = null
        avatarLoaderUtil = null
        screenChanger = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
        configureUtils()
        configureViewModel()
        configureViews()
        setClickListeners()
    }

    private fun setClickListeners() {
        logoutButton.setOnClickListener {
            profileViewModel?.logout()
        }
    }

    private fun configureUtils() {
        authStorageUtil = AuthStorageUtil(requireContext())
        avatarLoaderUtil = AvatarLoaderUtil(requireContext())
    }

    private fun configureViews() = CoroutineScope(Dispatchers.Main).launch {
        val user = withContext(Dispatchers.IO) {
            authStorageUtil!!.getSavedUser()
        }

        avatarLoaderUtil?.loadUserAvatar(avatarImageView)
        usernameTextView.text = user?.username
        emailTextView.text = user?.email
    }

    private fun configureViewModel() {
        profileViewModel = ProfileViewModel(requireContext())

        profileViewModel?.isLoggedIn?.observe(viewLifecycleOwner) { isLoggedIn ->
            if (!isLoggedIn) screenChanger?.changeScreen(AuthScreenFragment())
        }
    }

    private fun initializeViews() {
        avatarImageView = requireView().findViewById(R.id.avatarImageView)
        usernameTextView = requireView().findViewById(R.id.usernameTextView)
        emailTextView = requireView().findViewById(R.id.emailTextView)
        logoutButton = requireView().findViewById(R.id.logoutButton)
    }
}
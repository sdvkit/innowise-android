package com.sdv.kit.taskard.screen

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.sdv.kit.taskard.R
import com.sdv.kit.taskard.contract.ScreenChanger
import com.sdv.kit.taskard.viewmodel.AuthViewModel

class AuthScreenFragment : Fragment(R.layout.fragment_auth_screen) {
    private lateinit var googleSignButton: AppCompatButton

    private var googleSignInClient: GoogleSignInClient? = null
    private var googleActivityResultLauncher: ActivityResultLauncher<Intent>? = null
    private var authViewModel: AuthViewModel? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        registerGoogleActivityResultLauncher()
    }

    override fun onDetach() {
        super.onDetach()
        googleSignInClient = null
        googleActivityResultLauncher = null
        authViewModel = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
        setClickListeners()
        configureViewModel()
        configureGoogleSignInClient()
    }

    private fun configureViewModel() {
        authViewModel = AuthViewModel(requireContext())
    }

    private fun configureGoogleSignInClient() {
        val googleSignInOptions = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireContext(), googleSignInOptions)
    }

    private fun registerGoogleActivityResultLauncher() {
        googleActivityResultLauncher = registerForActivityResult(StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) try {
                authViewModel!!.saveUser()
                openMainScreen()
            } catch (_: ApiException) { }
        }
    }

    private fun openMainScreen() {
        (requireContext() as ScreenChanger).changeScreen(MainScreenFragment())
    }

    private fun setClickListeners() {
        googleSignButton.setOnClickListener {
            googleActivityResultLauncher?.launch(googleSignInClient!!.signInIntent)
        }
    }

    private fun initializeViews() {
        googleSignButton = requireView().findViewById(R.id.googleSignButton)
    }
}
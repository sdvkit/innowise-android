package com.sdv.kit.taskard.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.sdv.kit.taskard.entity.User
import com.sdv.kit.taskard.util.AuthStorageUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel(context: Context) : ViewModel() {
    private val authStorageUtil: AuthStorageUtil by lazy {
        AuthStorageUtil(context)
    }

    private val googleAccount: GoogleSignInAccount by lazy {
        GoogleSignIn.getLastSignedInAccount(context)!!
    }

    fun saveUser() = CoroutineScope(Dispatchers.IO).launch {
        val user = User.Builder()
            .id(googleAccount.id!!)
            .email(googleAccount.email!!)
            .username(googleAccount.displayName!!)
            .avatarUrl(googleAccount.photoUrl.toString())
            .build()

        authStorageUtil.saveUser(user)
    }
}
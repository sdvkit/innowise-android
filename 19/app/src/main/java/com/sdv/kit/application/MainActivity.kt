package com.sdv.kit.application

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.sdv.kit.application.entity.Profile
import com.sdv.kit.application.viewmodel.ProfileViewModel

class MainActivity : AppCompatActivity() {
    private var profileHeader: TextView? = null
    private var profileName: TextView? = null
    private var timeLabel: TextView? = null
    private var recommendationsCount: TextView? = null
    private var viewsCount: TextView? = null
    private var commentsCount: TextView? = null
    private var likesCount: TextView? = null
    private var backgroundView: View? = null
    private var avatar: View? = null

    private val profileViewModel: ProfileViewModel by lazy { ProfileViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hideStatusBar()
        initializeViews()
        setUpViewModel()
    }

    private fun setUpViewModel() {
        profileViewModel.startProfileUpdating()
        profileViewModel.currentProfile.observe(this) { profile -> changeViewsContent(profile) }
    }

    @SuppressLint("SetTextI18n")
    private fun changeViewsContent(profile: Profile) {
        val currentPrimaryColor = ContextCompat.getColor(this, profile.profilePrimaryColor)

        profileHeader?.text = profile.profileHeader
        profileName?.text = profile.profileName
        timeLabel?.text = profile.days.toString()
        recommendationsCount?.text = "${profile.recommendationsCount} / 56 users"
        viewsCount?.text = profile.viewsCount.toString()
        commentsCount?.text = profile.commentsCount.toString()
        likesCount?.text = profile.likesCount.toString()
        backgroundView?.setBackgroundColor(currentPrimaryColor)
        avatar?.backgroundTintList = ColorStateList.valueOf(currentPrimaryColor)
    }

    private fun initializeViews() {
        profileHeader = findViewById(R.id.profileHeader)
        profileName = findViewById(R.id.profileName)
        timeLabel = findViewById(R.id.timeLabel)
        recommendationsCount = findViewById(R.id.recommendationsCount)
        viewsCount = findViewById(R.id.viewsCount)
        commentsCount = findViewById(R.id.commentsCount)
        likesCount = findViewById(R.id.likesCount)
        backgroundView = findViewById(R.id.backgroundView)
        avatar = findViewById(R.id.avatar)
    }

    private fun hideStatusBar() {
        WindowInsetsControllerCompat(window, window.decorView)
            .hide(WindowInsetsCompat.Type.statusBars())
    }
}
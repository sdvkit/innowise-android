package com.sdv.kit.application

import android.app.Fragment
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.sdv.kit.application.fragment.MainFragment
import com.sdv.kit.application.util.LogUtil

class MainActivity : AppCompatActivity() {
    private val logUtil = LogUtil(MAIN_ACTIVITY_LOG_TAG)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setFragment()
        logUtil.logMethod()
    }

    private fun setFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, MainFragment())
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val result = super.onCreateOptionsMenu(menu)
        logUtil.logMethod()
        return result
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        logUtil.logMethod()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        logUtil.logMethod()
    }

    override fun onAttachFragment(fragment: Fragment?) {
        super.onAttachFragment(fragment)
        logUtil.logMethod()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        logUtil.logMethod()
    }

    override fun onContentChanged() {
        super.onContentChanged()
        logUtil.logMethod()
    }

    override fun onDestroy() {
        super.onDestroy()
        logUtil.logMethod()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        logUtil.logMethod()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        logUtil.logMethod()
    }

    override fun onPause() {
        super.onPause()
        logUtil.logMethod()
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
        logUtil.logMethod()
    }

    override fun onPostResume() {
        super.onPostResume()
        logUtil.logMethod()
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val result = super.onPrepareOptionsMenu(menu)
        logUtil.logMethod()
        return result
    }

    override fun onRestart() {
        super.onRestart()
        logUtil.logMethod()
    }

    override fun onResume() {
        super.onResume()
        logUtil.logMethod()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        logUtil.logMethod()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        logUtil.logMethod()
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        logUtil.logMethod()
    }

    override fun onStart() {
        super.onStart()
        logUtil.logMethod()
    }

    override fun onStop() {
        super.onStop()
        logUtil.logMethod()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        logUtil.logMethod()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        logUtil.logMethod()
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        logUtil.logMethod()
    }

    companion object {
        private const val MAIN_ACTIVITY_LOG_TAG = "MainActivity"
    }
}
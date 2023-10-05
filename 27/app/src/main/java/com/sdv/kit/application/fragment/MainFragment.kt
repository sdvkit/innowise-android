package com.sdv.kit.application.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sdv.kit.application.R
import com.sdv.kit.application.util.LogUtil

class MainFragment : Fragment(R.layout.fragment_main) {
    private val logUtil = LogUtil(MAIN_FRAGMENT_LOG_TAG)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        logUtil.logMethod()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val result = super.onCreateView(inflater, container, savedInstanceState)
        logUtil.logMethod()
        return result
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logUtil.logMethod()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        logUtil.logMethod()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        logUtil.logMethod()
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        logUtil.logMethod()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        logUtil.logMethod()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logUtil.logMethod()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        logUtil.logMethod()
    }

    override fun onDestroy() {
        super.onDestroy()
        logUtil.logMethod()
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        logUtil.logMethod()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        logUtil.logMethod()
    }

    override fun onDetach() {
        super.onDetach()
        logUtil.logMethod()
    }

    override fun onInflate(context: Context, attrs: AttributeSet, savedInstanceState: Bundle?) {
        super.onInflate(context, attrs, savedInstanceState)
        logUtil.logMethod()
    }

    override fun onInflate(activity: Activity, attrs: AttributeSet, savedInstanceState: Bundle?) {
        super.onInflate(activity, attrs, savedInstanceState)
        logUtil.logMethod()
    }

    override fun onPause() {
        super.onPause()
        logUtil.logMethod()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        logUtil.logMethod()
    }

    override fun onResume() {
        super.onResume()
        logUtil.logMethod()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
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

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        logUtil.logMethod()
    }

    companion object {
        private const val MAIN_FRAGMENT_LOG_TAG = "MainFragment"
    }
}
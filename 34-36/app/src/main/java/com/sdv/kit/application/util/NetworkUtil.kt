package com.sdv.kit.application.util

import android.content.Context

class NetworkUtil(private val context: Context) {
    fun isInternetConnectionPresents(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as android.net.ConnectivityManager
        return with (
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        ) {
            this?.let {
                if (hasTransport(android.net.NetworkCapabilities.TRANSPORT_CELLULAR)) return@with true
                else if (hasTransport(android.net.NetworkCapabilities.TRANSPORT_WIFI)) return@with true
                else if (hasTransport(android.net.NetworkCapabilities.TRANSPORT_ETHERNET)) return@with true
            }
            return false
        }
    }
}
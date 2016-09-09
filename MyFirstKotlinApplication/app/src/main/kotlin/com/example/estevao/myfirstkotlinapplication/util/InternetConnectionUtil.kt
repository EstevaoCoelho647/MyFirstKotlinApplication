package com.example.estevao.myfirstkotlinapplication.util

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by estevao on 06/09/16.
 */

fun isConnected(context: Context): Boolean {
    val conMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = conMgr.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected) {
        return true
    } else {
        return false
    }
}

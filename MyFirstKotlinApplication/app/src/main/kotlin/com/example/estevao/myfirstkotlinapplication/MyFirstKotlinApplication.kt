package com.example.estevao.myfirstkotlinapplication

import android.app.Application

/**
 * Created by estevao on 06/09/16.
 */
class ApiGitManagerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ApplicationUtil.context = applicationContext
    }
}


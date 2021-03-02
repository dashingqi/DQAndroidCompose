package com.example.androiddevchallenge.app

import android.app.Application
import android.content.Context

/**
 * @author : zhangqi
 * @time : 2021/3/2
 * desc :
 */
class ProjectApp : Application() {

    companion object {
        lateinit var mApp: Context
    }

    override fun onCreate() {
        super.onCreate()
        mApp = applicationContext
    }
}
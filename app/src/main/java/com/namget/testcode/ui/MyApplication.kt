package com.namget.testcode.ui

import android.app.Application
import com.namget.testcode.data.model.Auth
import com.namget.testcode.di.apiModule
import com.namget.testcode.di.viewModelModule
import com.namget.testcode.ext.startDIKoin

/**
 * Created by Namget on 2019.09.23.
 */
class MyApplication : Application() {

    override fun onCreate() {
        init()
        super.onCreate()
    }

    private fun init() {
        startDIKoin(this@MyApplication, listOf(apiModule, viewModelModule))
        Auth.CREATE(this.applicationContext)
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}
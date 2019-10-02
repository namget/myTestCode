package com.namget.testcode.ext

import android.content.Context
import com.namget.testcode.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger
import org.koin.core.module.Module

/**
 * Created by Namget on 2019.09.30.
 */
fun startDIKoin(context: Context , modules : List<Module>) {
    startKoin {
        logger(if (BuildConfig.DEBUG) AndroidLogger() else EmptyLogger())
        androidContext(context)
        modules(modules)
    }
}

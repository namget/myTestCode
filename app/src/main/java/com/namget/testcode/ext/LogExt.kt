package com.namget.testcode.ext

import android.util.Log
import com.namget.testcode.BuildConfig

/**
 * Created by Namget on 2019.10.23.
 */
internal fun error(TAG: String, message: String) {
    if (BuildConfig.DEBUG) {
        Log.e(TAG, message)
    }
}

internal fun error(TAG: String, message: String, e: Exception?) {
    if (BuildConfig.DEBUG) {
        Log.e(TAG, message, e)
    }
}

internal fun debug(TAG: String, message: String) {
    if (BuildConfig.DEBUG) {
        Log.d(TAG, message)
    }
}

internal fun debug(TAG: String, message: String, e: Exception?) {
    if (BuildConfig.DEBUG) {
        Log.d(TAG, message, e)
    }
}

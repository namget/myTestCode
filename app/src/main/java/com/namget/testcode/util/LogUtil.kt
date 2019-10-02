package com.namget.testcode.util

import android.util.Log
import com.namget.testcode.BuildConfig

/**
 * Created by Namget on 2019.09.23.
 */

public fun e(TAG: String, message: String) {
    if (BuildConfig.DEBUG) {
        Log.e(TAG, message)
    }
}

public fun e(TAG: String, message: String, e: Exception?) {
    if (BuildConfig.DEBUG) {
        Log.e(TAG, message, e)
    }
}

public fun d(TAG: String, message: String) {
    if (BuildConfig.DEBUG) {
        Log.d(TAG, message)
    }
}

public fun d(TAG: String, message: String, e: Exception?) {
    if (BuildConfig.DEBUG) {
        Log.d(TAG, message, e)
    }
}


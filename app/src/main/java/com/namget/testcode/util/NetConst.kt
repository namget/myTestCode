package com.namget.testcode.util

import com.namget.testcode.BuildConfig

object NetConst {
    private const val HOST_REAL = "https://bfs1.bfservice.co.kr/"
    private const val HOST_DEBUG = "https://bfs1.bfservice.co.kr/"
    val host: String

    init {
        host = if (BuildConfig.DEBUG) HOST_DEBUG else HOST_REAL
    }

}
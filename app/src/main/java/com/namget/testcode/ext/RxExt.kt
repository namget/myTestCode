package com.namget.testcode.ext

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Namget on 2019.10.01.
 */
fun <T> Single<T>.withScheduler(): Single<T> = subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
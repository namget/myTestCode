package com.namget.testcode.ext

import io.reactivex.Single

/**
 * Created by Namget on 2019.10.10.
 */

fun<T> Single<T>.toResult() : Single<Result<T>>{
    return this.map { Result.success(it) }
}
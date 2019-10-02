package com.namget.testcode.data.source

import io.reactivex.Flowable
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import io.reactivex.rxkotlin.Flowables
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit


/**
 * Created by Namget on 2019.10.01.
 */
internal typealias RETRY_PREDICATE = (Throwable) -> Boolean

internal const val MAX_RETRIES = 3L  //3번
internal const val DEFAULT_INTERVAL = 1L // 1초간격

internal val TIMEOUT: RETRY_PREDICATE = { it is SocketTimeoutException } // timeout 에러
internal val NETWORK: RETRY_PREDICATE = { it is IOException }  //network IO에러
internal val SERVICE_UNAVAILABLE: RETRY_PREDICATE =
    { it is HttpException && it.code() == 503 } //503 에러

//재시작을 위한 전략코드
internal fun <T> applyRetryPolicy(
    vararg predicates: RETRY_PREDICATE = arrayOf(TIMEOUT, NETWORK),
    maxRetries: Long = MAX_RETRIES,
    interval: Long = DEFAULT_INTERVAL,
    unit: TimeUnit = TimeUnit.SECONDS,
    resumeNext: (Throwable) -> SingleSource<T>
) = SingleTransformer<T, T> { single ->
    single.retryWhen { attempts ->
        Flowables.zip(
            attempts.map { error -> if (predicates.count { it(error) } > 0) error else throw error },
            Flowable.interval(interval, unit)
        ).map { (error, retryCount) -> if (retryCount >= maxRetries) throw error }
    }.onErrorResumeNext(resumeNext)
}
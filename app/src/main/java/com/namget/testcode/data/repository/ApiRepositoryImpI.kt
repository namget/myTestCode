package com.namget.testcode.data.repository

import com.namget.testcode.data.model.response.LoginResonse
import com.namget.testcode.data.model.response.StorageResponse
import com.namget.testcode.data.source.remote.ApiRemoteDatasource
import com.namget.testcode.ext.withScheduler
import io.reactivex.Single
import io.reactivex.SingleSource

/**
 * Created by Namget on 2019.09.23.
 */
class ApiRepositoryImpI(private val apiRemoteDatasource: ApiRemoteDatasource) :
    ApiRepository {
    override fun getLoginInfo(id: String, pwd: String): Single<Result<LoginResonse>> {
        return apiRemoteDatasource.getLoginInfo(id, pwd)
            .map { Result.success(it) }
            .withScheduler()
    }

    override fun getStorageInfo(): Single<Result<StorageResponse>> {
        return apiRemoteDatasource.getStorageInfo()
            .map { response -> Result.success(response) }
            .onErrorResumeNext {
                SingleSource { emmiter ->
                    Single.concat(
                        apiRemoteDatasource.getLoginInfo(
                            "tt",
                            "tt"
                        ), (apiRemoteDatasource.getStorageInfo())
                    ).subscribe({
                        //로그인 관련 Auth 처리
                        if (it is LoginResonse) {

                        }
                        //Storage 처리
                        if (it is StorageResponse) {
                            emmiter.onSuccess(Result.success(it))
                        }
                    }, {
                        emmiter.onError(it)
                    })
                }
            }
            .withScheduler()
    }

}
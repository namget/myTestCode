package com.namget.testcode.data.repository


import com.namget.testcode.data.model.Auth
import com.namget.testcode.data.model.dto.response.LoginResonse
import com.namget.testcode.data.model.dto.response.StorageResponse
import com.namget.testcode.data.source.local.ApiLocalLocalDataSourceImpl
import com.namget.testcode.data.source.remote.ApiRemoteDataSourceImpl
import com.namget.testcode.ext.withScheduler
import io.reactivex.Single
import io.reactivex.SingleSource

/**
 * Created by Namget on 2019.09.23.
 */
class ApiRepositoryImpI(
    private val apiRemoteDataSourceImpl: ApiRemoteDataSourceImpl,
    private val apiLocalDataSourceImpl: ApiLocalLocalDataSourceImpl
) : ApiRepository {
    override fun getLoginInfo(id: String, pwd: String): Single<Result<LoginResonse.Login>> {
        return apiRemoteDataSourceImpl.getLoginInfo(id, pwd)
            .map {
                if (it.resultData == null) {
                    Result.success(it.loginResonseToLogin())
                } else {
                    Result.success(it.resultDatatoLogin())
                }
            }
            //error 시 에러 내용을 next에 반환
            .onErrorResumeNext { Single.just(Result.failure(it)) }
            .withScheduler()
    }

    override fun getStorageInfo(): Single<Result<StorageResponse>> {
        return apiRemoteDataSourceImpl.getStorageInfo()
            .map { response -> Result.success(response) }
            .onErrorResumeNext {
                SingleSource { emmiter ->
                    Single.concat(
                        apiRemoteDataSourceImpl.getLoginInfo(
                            Auth.loadIDInfo(),
                            Auth.loadPWInfo()
                        ), (apiRemoteDataSourceImpl.getStorageInfo())
                    ).subscribe({
                        //로그인 관련 Auth 처리
                        if (it is LoginResonse) {
                            //TODO 로그인 관련 처리
                        }
                        //Storage 처리
                        if (it is StorageResponse) {
                            emmiter.onSuccess(Result.success(it))
                        }
                    }, {
                        //에러일시 onSuccess를 통해 failure 결과값을 반환
                        emmiter.onSuccess(Result.failure(it))
                    })
                }
            }
            .withScheduler()
    }

}
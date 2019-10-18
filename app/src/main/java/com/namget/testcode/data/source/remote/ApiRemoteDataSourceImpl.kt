package com.namget.testcode.data.source.remote


import com.namget.testcode.data.model.dto.response.LoginResonse
import com.namget.testcode.data.model.dto.response.StorageResponse
import io.reactivex.Single

/**
 * Created by Namget on 2019.09.23.
 */
class ApiRemoteDataSourceImpl(val apiService: ApiService) :
    ApiRemoteDataSource {
    override fun getLoginInfo(id: String, pwd: String): Single<LoginResonse> {
        return apiService.getLogin(id, pwd)
    }

    override fun getStorageInfo() : Single<StorageResponse>{
        return apiService.getStorageInfo()
    }
}
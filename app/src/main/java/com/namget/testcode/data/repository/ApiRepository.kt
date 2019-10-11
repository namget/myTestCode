package com.namget.testcode.data.repository

import com.namget.testcode.data.model.response.LoginResonse
import com.namget.testcode.data.model.response.StorageResponse
import io.reactivex.Single

interface ApiRepository {
    fun getLoginInfo(id : String , pwd : String) : Single<Result<LoginResonse>>
    fun getStorageInfo() : Single<Result<StorageResponse>>
}
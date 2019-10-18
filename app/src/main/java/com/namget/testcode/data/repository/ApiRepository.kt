package com.namget.testcode.data.repository

import com.namget.testcode.data.model.dto.response.LoginResonse
import com.namget.testcode.data.model.dto.response.StorageResponse
import io.reactivex.Single

interface ApiRepository {
    fun getLoginInfo(id : String , pwd : String) : Single<Result<LoginResonse.Login>>
    fun getStorageInfo() : Single<Result<StorageResponse>>
}
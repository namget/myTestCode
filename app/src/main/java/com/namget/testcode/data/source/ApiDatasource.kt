package com.namget.testcode.data.source

import com.namget.testcode.data.model.dto.response.LoginResonse
import com.namget.testcode.data.model.dto.response.StorageResponse
import io.reactivex.Single

/**
 * Created by Namget on 2019.09.23.
 */
interface ApiDatasource {
    fun getLoginInfo(id : String , pwd : String) : Single<LoginResonse>
    fun getStorageInfo() : Single<StorageResponse>
}
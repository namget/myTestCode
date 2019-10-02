package com.namget.testcode.data.source.remote

import com.namget.testcode.data.model.response.LoginResonse
import com.namget.testcode.data.source.ApiDatasource
import io.reactivex.Single

/**
 * Created by Namget on 2019.09.23.
 */
class ApiRemoteDatasource(val apiService: ApiService) : ApiDatasource {
    override fun getLoginInfo(id: String, pwd: String): Single<LoginResonse> {
        return apiService.getLogin(id, pwd)
    }

    override fun getStorageInfo() {

    }
}
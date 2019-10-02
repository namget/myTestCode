package com.namget.testcode.data.source

import com.namget.testcode.data.model.response.LoginResonse
import com.namget.testcode.data.source.remote.ApiRemoteDatasource
import com.namget.testcode.ext.withScheduler
import io.reactivex.Single

/**
 * Created by Namget on 2019.09.23.
 */
class ApiRepository(val apiRemoteDatasource: ApiRemoteDatasource) : ApiDatasource {
    override fun getLoginInfo(id: String, pwd: String): Single<LoginResonse> {
        return apiRemoteDatasource.getLoginInfo(id, pwd)
            .withScheduler()
    }

    override fun getStorageInfo() {
        //
    }
}
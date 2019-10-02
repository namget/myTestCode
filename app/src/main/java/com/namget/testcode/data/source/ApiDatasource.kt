package com.namget.testcode.data.source

import com.namget.testcode.data.model.response.LoginResonse
import io.reactivex.Single

/**
 * Created by Namget on 2019.09.23.
 */
interface ApiDatasource {
    fun getLoginInfo(id : String , pwd : String) : Single<LoginResonse>
    fun getStorageInfo()
}
package com.namget.testcode.data.source.remote

import com.namget.testcode.data.model.response.LoginResonse
import com.namget.testcode.data.model.response.StorageResponse
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


/**
 * Created by Namget on 2019.09.30.
 */
interface ApiService {


    /**
     * 로그인
     */
    @FormUrlEncoded
    @POST("login/appLogin.json")
    fun getLogin(
        @Field("admin_id") id: String, @Field("admin_pwd") pw: String
    ): Single<LoginResonse>



    @POST("/mobile/api/wms/selectWarehouseList.json")
    fun getStorageInfo() : Single<StorageResponse>
}
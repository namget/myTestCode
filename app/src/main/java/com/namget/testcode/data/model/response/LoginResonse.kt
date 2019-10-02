package com.namget.testcode.data.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Namget on 2019.09.30.
 */
data class LoginResonse(
    override var resultMsg: String? = null,
    override var resultCode: String? = null,
    @Expose
    val resultData: ResultData? = null
) : BaseResponse() {
    data class ResultData(
        @SerializedName("CAR_NUMBER") val carNumber: String
        , @SerializedName("TEL_NO") val telNo: String
        , @SerializedName("resultMsg") val resultMsg: String
        , @SerializedName("SID") val sid: String
        , @SerializedName("PER_CD") val perCd: Int
        , @SerializedName("GROUP_CD") val groupCd: String
        , @SerializedName("ADMIN_NM") val adminName: String
        , @SerializedName("COMPANY_NM") val companyName: String
        , @SerializedName("resultCode") val resultCode: String
        , @SerializedName("IPHONE_UUID") val iphoneUuid: String
        , @SerializedName("RECEIVE_ALERT") val receiveAlert: String
        , @SerializedName("loginDate") val loginDate: String
        , @SerializedName("PUSH_REGID") val pushRegId: String
        , @SerializedName("WH_SEQ") val wgSeq: String
        , @SerializedName("ADMIN_ID") val adminId: String
    )
}
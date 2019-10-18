package com.namget.testcode.data.model.dto.request

import com.google.gson.annotations.SerializedName

/**
 * Created by Namget on 2019.09.30.
 */

data class LoginRequest(
    @SerializedName("admin_id")
    val adminId : String,
    @SerializedName("admin_pwd")
    val adminPwd: String
)
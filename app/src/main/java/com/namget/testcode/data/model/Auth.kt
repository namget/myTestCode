package com.namget.testcode.data.model

import android.content.Context
import android.content.SharedPreferences
import com.namget.testcode.data.model.dto.response.LoginResonse

/**
 * Created by Namget on 2019.09.30.
 */
class Auth private constructor() {

    companion object {
        private val authFileName = "auth"
        private lateinit var preferences: SharedPreferences
        private var resultData: LoginResonse.ResultData? = null

        enum class LoginDataEnum(val key: String) {
            IS_AUTO_LOGIN("IS_AUTO_LOGIN"),
            ID("ID"),
            PW("PW"),
            SID("SID"),
            PER_CD("PER_CD"),
            GROUP_CD("GROUP_CD"),
            COMPANY_NM("COMPANY_NM"),
            ADMIN_NM("ADMIN_NM"),
            LOGIN_DATE("LOGIN_DATE"),
            ADMIN_ID("ADMIN_ID"),
            CAR_NUMBER("CAR_NUMBER")
        }

        fun CREATE(context: Context) {
            preferences = context.getSharedPreferences(authFileName, Context.MODE_PRIVATE)
        }

        fun loadIDInfo(): String = preferences.getString(LoginDataEnum.ID.key, "") ?: ""
        fun loadPWInfo(): String = preferences.getString(LoginDataEnum.PW.key, "") ?: ""
        fun saveIdPw(id: String, pw: String) = preferences.edit().apply {
            putString(LoginDataEnum.ID.key, id)
            putString(LoginDataEnum.PW.key, pw)
            apply()
        }


        fun saveAutoLogin(isLogin: Boolean) = preferences.edit().apply {
            putBoolean(LoginDataEnum.IS_AUTO_LOGIN.key, isLogin)
            apply()
        }

        fun loadIsAutoLogin(): Boolean = preferences.getBoolean(LoginDataEnum.IS_AUTO_LOGIN.key, false)

        fun logout() = preferences.edit().clear().apply()


        fun saveLoginDataPref(rd: LoginResonse.ResultData) =
            preferences.edit().apply {
                putString(LoginDataEnum.SID.key, rd.sid)
                putInt(LoginDataEnum.PER_CD.key, rd.perCd)
                putString(LoginDataEnum.GROUP_CD.key, rd.groupCd)
                putString(LoginDataEnum.COMPANY_NM.key, rd.companyName)
                putString(LoginDataEnum.ADMIN_NM.key, rd.adminName)
                putString(LoginDataEnum.LOGIN_DATE.key, rd.loginDate)
                putString(LoginDataEnum.ADMIN_ID.key, rd.adminId)
                putString(LoginDataEnum.CAR_NUMBER.key, rd.carNumber)
                apply()
            }


        fun checkSession(resultCode: Int): Boolean {
            if (resultCode == 309) {
                resultData = null
                return true
            } else {
                return false
            }
        }

    }
}
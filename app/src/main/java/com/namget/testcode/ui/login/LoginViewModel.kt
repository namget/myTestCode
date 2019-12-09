package com.namget.testcode.ui.login

import android.util.Log.e
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.namget.testcode.R
import com.namget.testcode.data.model.Auth
import com.namget.testcode.data.repository.ApiRepository
import com.namget.testcode.ext.plusAssign
import com.namget.testcode.ui.base.BaseViewModel
import com.namget.testcode.util.Event
import com.namget.testcode.util.START_MAIN_ACTIVITY

/**
 * Created by Namget on 2019.09.30.
 */
class LoginViewModel(private val apiRepository: ApiRepository) : BaseViewModel() {

    //양방향 데이터 바인딩
    val idText: MutableLiveData<String> = MutableLiveData()
    //양방향 데이터 바인딩
    val passwordText: MutableLiveData<String> = MutableLiveData()
    //양방향 데이터 바인딩
    val isAutoLogin: MutableLiveData<Boolean> = MutableLiveData()

    private val _startMainActivityEvent: MutableLiveData<Event<String>> = MutableLiveData()
    val startMainActivityEvent: LiveData<Event<String>> get() = _startMainActivityEvent


    init {
        setTestLoginInfo()
        autoLogin()
    }

    /**
     * 테스트 아이디 자동 입력
     */
    private fun setTestLoginInfo(){
        idText.value = "ycm83"
        passwordText.value = "body596!"
    }

    /**
     * 자동로그인 시도
     */
    private fun autoLogin(){
        if (Auth.loadIsAutoLogin()) {
            val id = Auth.loadIDInfo()
            val password = Auth.loadPWInfo()
            idText.value = id
            passwordText.value = password
            isAutoLogin.value = Auth.loadIsAutoLogin()
            requestLogin(id, password)
        }
    }

    /**
     * 로그인 버튼 클릭시
     */
    fun login() {
        e("test", "id : " + idText.value)
        val id = idText.value ?: ""
        val pw = passwordText.value ?: ""

        if (!checkLoginText(id, pw)) {
            requestLogin(id, pw)
        } else {
            showToastMessage(R.string.not_inputted_user_error)
        }
    }


    /**
     * 로그인 네트워크 메소드
     */
    private fun requestLogin(id: String, pw: String) {
        isLoading(true)
        compositeDisposable += apiRepository.getLoginInfo(id, pw)
            .subscribe { result ->
                isLoading(false)
                result.fold({
                    e("requestLogin", "success : $it")
                    if (result != null) {
                        //자동로그인시
                        if (isAutoLogin.value == true) {
                            saveAuthInfo(id, pw)
                        }
                        _startMainActivityEvent.value = Event(START_MAIN_ACTIVITY)
                    } else {
                        //error show
                        showToastMessage(R.string.network_error)
                    }
                }, {
                    e("requestLogin", "error : $it")
                    showToastMessage(R.string.network_error)
                })
            }
    }

    /**
     * 로그인 아이디, 비밀번호 저장
     */
    private fun saveAuthInfo(id: String, pw: String) {
        Auth.saveAutoLogin(isAutoLogin.value ?: false)
        Auth.saveIdPw(id, pw)
    }

    /**
     * 로그인 텍스트 체크
     */
    private fun checkLoginText(id: String, password: String): Boolean {
        return (id.isEmpty() || password.isEmpty())
    }

}
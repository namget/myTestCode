package com.namget.testcode.ui.login

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.namget.testcode.R
import com.namget.testcode.data.model.Auth
import com.namget.testcode.data.repository.ApiRepository
import com.namget.testcode.ext.plusAssign
import com.namget.testcode.ui.base.BaseViewModel
import com.namget.testcode.util.Event
import com.namget.testcode.util.e

/**
 * Created by Namget on 2019.09.30.
 */
class LoginViewModel(private val apiRepository: ApiRepository) : BaseViewModel() {

    private val _idText: MutableLiveData<String> = MutableLiveData()
    val idText: LiveData<String> get() = _idText

    private val _passwordText: MutableLiveData<String> = MutableLiveData()
    val passwordText: LiveData<String> get() = _passwordText

    private val _startMainActivityEvent: MutableLiveData<Event<String>> = MutableLiveData()
    val startMainActivityEvent: LiveData<Event<String>> get() = _startMainActivityEvent

    private val _isAutoLogin: MutableLiveData<Boolean> = MutableLiveData()
    val isAutoLogin: LiveData<Boolean> get() = _isAutoLogin


    init {
        /*_idText.value ="ycm83"
        _passwordText.value ="body596!"*/

        if (Auth.loadIsAutoLogin()) {
            val id = Auth.loadIDInfo()
            val password = Auth.loadPWInfo()
            _idText.value = id
            _passwordText.value = password
            _isAutoLogin.value = Auth.loadIsAutoLogin()
            requestLogin(id, password)
        }
    }

    fun login(view: View) {
        val id = _idText.value ?: ""
        val pw = _passwordText.value ?: ""

        if (!checkLoginText(id, pw)) {
            requestLogin(id, pw)
        } else {
            showToastMessage(R.string.not_inputted_user_error)
        }
    }


    fun requestLogin(id: String, pw: String) {
        isLoading(true)
        compositeDisposable += apiRepository.getLoginInfo(id, pw)
            .subscribe { result ->
                isLoading(false)
                result.fold({
                    e("requestLogin", "success : $it")
                    if (result != null) {
                        saveAuthInfo(id, pw)
                        _startMainActivityEvent.value = Event("")
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

    private fun saveAuthInfo(id: String, pw: String) {
        Auth.saveAutoLogin(_isAutoLogin.value ?: false)
        Auth.saveIdPw(id, pw)
    }

    private fun checkLoginText(id: String, password: String): Boolean {
        return (id.isEmpty() || password.isEmpty())
    }

    fun onAutoLoginCheckedChanged(view: View, isChecked: Boolean) {
        e("onAutoLoginCheckedChanged", "onAutoLoginCheckedChanged : $isChecked")
        _isAutoLogin.value = (isChecked)
    }

    fun loginIdTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        e("loginIdTextChanged", "loginIdTextChanged : $s")
        _idText.value = s.toString()
    }

    fun loginPwTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        e("loginPwTextChanged", "loginPwTextChanged : $s")
        _passwordText.value = s.toString()
    }


}
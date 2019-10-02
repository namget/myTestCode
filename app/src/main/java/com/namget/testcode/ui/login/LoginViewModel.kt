package com.namget.testcode.ui.login

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.namget.testcode.R
import com.namget.testcode.data.model.Auth
import com.namget.testcode.data.source.ApiRepository
import com.namget.testcode.ext.plusAssign
import com.namget.testcode.ui.base.BaseViewModel
import com.namget.testcode.util.Event

/**
 * Created by Namget on 2019.09.30.
 */
class LoginViewModel(private val apiRepository: ApiRepository) : BaseViewModel() {

    private val _IdText: MutableLiveData<String> = MutableLiveData()
    val loginText: LiveData<String> get() = _IdText

    private val _passwordText: MutableLiveData<String> = MutableLiveData()
    val passwordText: LiveData<String> get() = _passwordText

    private val _startActivityEvent: MutableLiveData<Event<String>> = MutableLiveData()
    val startActivityEvent: LiveData<Event<String>> get() = _startActivityEvent

    private val _isAutoLogin: MutableLiveData<Boolean> = MutableLiveData()
    val isAutoLogin: LiveData<Boolean> get() = _isAutoLogin


    init {
        /*println("PP.autoLogin.`is`() ${PP.autoLogin.`is`()}")
        if (PP.autoLogin.`is`() && Auth.getId().isNotEmpty() && Auth.getPw().isNotEmpty()) {
            login(Auth.getId(), Auth.getPw())
        }*/
    }

    fun login(view: View) {
        val id = _IdText.value ?: ""
        val pw = _passwordText.value ?: ""

        if (checkLoginText(id, pw)) {
            requestLogin(id, pw)
        } else {

        }
    }

    fun requestLogin(id: String, pw: String) {
        isLoading(true)
        compositeDisposable += apiRepository.getLoginInfo(id, pw)
            .subscribe({ result ->
                isLoading(false)
                if (result.resultData != null) {
                    if (_isAutoLogin.value == true) {
                        Auth.login(result.resultData)
                        Auth.saveIdPw(id, pw)
                    }
                } else {
                    showSnackbarMessage(R.string.network_error)
                    //error show
                }
            }, {
                showSnackbarMessage(R.string.network_error)
                isLoading(false)
            })
    }

    fun checkLoginText(id: String, password: String): Boolean {
        return (id.isEmpty() || password.isEmpty())
    }

    fun onAutoLogin(view: View, isChecked: Boolean) {
        println("onAutoLogin : $isChecked")
        PP.autoLogin.set(isChecked)
        _isAutoLogin.value = (isChecked)
    }

    fun loginIdTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        _IdText.value = s.toString()
    }

    fun loginPwTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        _passwordText.value = s.toString()
    }


}
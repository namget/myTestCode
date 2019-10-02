package com.namget.testcode.ui.base

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.namget.testcode.util.Event
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    private val _toastMessage: MutableLiveData<Event<Int>> = MutableLiveData()
    val toastMessage: LiveData<Event<Int>> get() = _toastMessage

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> get() = _isLoading

    val compositeDisposable = CompositeDisposable()


    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    protected fun isLoading(loading : Boolean){
        _isLoading.value = loading
    }

    protected fun showSnackbarMessage(@StringRes message: Int) {
        _toastMessage.value = Event(message)
    }
}
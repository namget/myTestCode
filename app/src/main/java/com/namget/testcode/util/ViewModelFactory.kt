package com.namget.testcode.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.namget.testcode.data.source.ApiRepository
import com.namget.testcode.ui.main.MainViewModel

/**
 * Created by Namget on 2019.09.23.
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory(val apiRepository: ApiRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = with(modelClass) {
        when {
            isAssignableFrom(MainViewModel::class.java) -> MainViewModel(apiRepository)
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T

}
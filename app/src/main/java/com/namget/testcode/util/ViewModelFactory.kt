package com.namget.testcode.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.namget.testcode.data.repository.ApiRepositoryImpI
import com.namget.testcode.ui.main.MainViewModel

/**
 * Created by Namget on 2019.09.23.
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory(val apiRepositoryImpI: ApiRepositoryImpI) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = with(modelClass) {
        when {
            isAssignableFrom(MainViewModel::class.java) -> MainViewModel(apiRepositoryImpI)
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T

}
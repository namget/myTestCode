package com.namget.testcode.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class BaseActivity<B : ViewDataBinding, T : ViewModel>(clazz : KClass<T>) : AppCompatActivity() {
    abstract fun onLayoutId(): Int
    protected val viewModel: T by viewModel(clazz)
    protected open lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, onLayoutId())
        binding.lifecycleOwner = this
    }

}
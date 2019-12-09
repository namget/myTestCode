package com.namget.testcode.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * Created by Namget on 2019.10.29.
 */
abstract class BaseFragment<B : ViewDataBinding> : Fragment() {

    abstract fun onLayoutId(): Int
    open lateinit var binding: B
    /***
     * 뷰모델 공유시에
     * private val weatherViewModel by sharedViewModel<WeatherViewModel>()
     *
     * 그냥 이용시
     * private val weatherViewModel by viewModel<WeatherViewModel>()
     *
     * 링크
     * https://insert-koin.io/docs/2.0/documentation/reference/index.html#_injecting_your_viewmodel
     */
//    private val shippingViewModel by viewModel(ShippingFragment::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, onLayoutId(), container, false, null)
        binding.lifecycleOwner = this
        return binding.root
    }

}
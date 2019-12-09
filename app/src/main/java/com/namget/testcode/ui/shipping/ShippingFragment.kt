package com.namget.testcode.ui.shipping

import android.os.Bundle
import com.namget.testcode.R
import com.namget.testcode.databinding.FragmentShippingBinding
import com.namget.testcode.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_delivery.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Namget on 2019.10.29.
 */
class ShippingFragment : BaseFragment<FragmentShippingBinding>() {
    override fun onLayoutId(): Int = R.layout.fragment_shipping
    private val shippingViewModel by viewModel<ShippingViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init(){
        initObserve()
        initRecyclerView()
    }

    private fun initObserve(){

//        NavHostFragment.findNavController(this).navigate(ShippingFragmentDirections.actionShippingFragmentToHomeFragment())
    }
    private fun initRecyclerView(){
        recyclerView.apply {
            setHasFixedSize(true)
        }
    }

}
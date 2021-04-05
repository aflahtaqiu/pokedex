package com.aflah.pokedex.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.aflah.pokedex.BaseApp
import com.aflah.pokedex.extension.isVisible
import com.aflah.pokedex.extension.showSnackBar

abstract class BaseFragment<T: ViewDataBinding, V: BaseViewModel>: Fragment() {

    lateinit var binding: T
    protected lateinit var viewModel: V

    abstract val layoutResource: Int

    abstract fun bindViewModel()
    abstract fun viewDidLoad()

    var loadingView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResource, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
        setRegisterObserver()
        viewDidLoad()
    }

    private fun setRegisterObserver() {
        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            loadingView?.isVisible = it
        })
        viewModel.message.observe(viewLifecycleOwner, Observer { message ->
            binding.root.showSnackBar(message, 4000)
        })
    }
}
package com.aflah.pokedex.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.aflah.pokedex.R
import com.aflah.pokedex.extension.isConnected
import com.aflah.pokedex.extension.isVisible
import com.aflah.pokedex.extension.showSnackBar
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseActivity<T: ViewDataBinding, V: BaseViewModel>: AppCompatActivity() {

    lateinit var binding: T
    lateinit var viewModel: V

    abstract val layoutResource: Int

    abstract fun bindViewModel()
    abstract fun viewDidLoad()

    var loadingView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpDataBinding()
        bindViewModel()
        setRegisterObserver()
        checkInternetConnection()
        viewDidLoad()
    }

    protected fun checkInternetConnection() {
        noInternetView.isVisible = !isConnected()
    }

    private fun setUpDataBinding() {
        binding = DataBindingUtil.setContentView(this, layoutResource)
        loadingView = binding.root.findViewById(R.id.loadingView)
    }

    private fun setRegisterObserver() {
        viewModel.isLoading.observe(this, Observer{
            loadingView?.isVisible = it
        })

        viewModel.message.observe(this, Observer{ message ->
            binding.root.showSnackBar(message, 4000)
        })
    }
}
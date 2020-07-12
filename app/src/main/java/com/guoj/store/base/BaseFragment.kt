package com.guoj.store.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import butterknife.Unbinder

abstract class BaseFragment: Fragment(){
    private var bind: Unbinder? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView:View?=loadRootView(inflater, container, savedInstanceState)
        bind = ButterKnife.bind(this,rootView!!)
        initView()
        initPresenter()
        loadData()
        return rootView
    }


    open protected fun initView() {

    }

    //加载数据
    open protected fun loadData() {

    }

    protected fun loadRootView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(getRootViewResId(), container, false)
    }

    abstract fun getRootViewResId(): Int

    open protected fun initPresenter(){

    }

    override fun onDestroyView() {
        super.onDestroyView()
        release()
    }

    open fun release() {

    }
}
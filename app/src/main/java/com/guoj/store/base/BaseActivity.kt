package com.guoj.store.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.guoj.store.R

public abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        initView()
        initEvent()
        initPresenter()
    }

    protected abstract fun initPresenter()
    /**
     * 需要的时候覆写
     */
    open protected fun initEvent() {

    }

    protected abstract fun initView()

    protected abstract fun getLayoutResId(): View
}
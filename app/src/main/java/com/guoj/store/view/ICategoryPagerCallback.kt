package com.guoj.store.view

import com.guoj.store.base.IBaseCallBack
import com.guoj.store.model.bean.Data

interface ICategoryPagerCallback:IBaseCallBack {
    fun onHomeContentLoaded(homePagerContents: List<Data>?)
    fun getCategoryId():String?
}
package com.guoj.store.view

import com.guoj.store.base.IBaseCallBack
import com.guoj.store.model.bean.Categories
import com.guoj.store.model.bean.HomePagerContent

interface ICategoryPagerCallback:IBaseCallBack {
    fun onHomeContentLoaded(homePagerContent: HomePagerContent?)
}
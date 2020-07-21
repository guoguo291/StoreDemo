package com.guoj.store.view

import com.guoj.store.base.IBaseCallBack
import com.guoj.store.model.bean.Categories

interface IHomeCallback :IBaseCallBack{
    fun onCategoriesLoaded(categories: Categories?)
}
package com.guoj.store.presenter

import com.guoj.store.base.IBasePresenter
import com.guoj.store.model.bean.Categories
import com.guoj.store.view.IHomeCallback

interface IHomePresenter<T> :IBasePresenter<IHomeCallback>{
    /**
     * 获取商品分类
     */
    fun getCategories()
}
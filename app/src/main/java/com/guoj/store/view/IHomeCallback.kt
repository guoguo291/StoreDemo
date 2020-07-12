package com.guoj.store.view

import com.guoj.store.model.bean.Categories

interface IHomeCallback {
    fun onCategoriesLoaded(categories: Categories?)
}
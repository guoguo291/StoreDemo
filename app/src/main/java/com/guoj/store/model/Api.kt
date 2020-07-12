package com.guoj.store.model

import com.guoj.store.model.bean.Categories
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("discovery/categories")
    fun getCategories():Call<Categories>
}
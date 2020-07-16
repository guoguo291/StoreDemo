package com.guoj.store.model

import com.guoj.store.model.bean.Categories
import com.guoj.store.model.bean.HomePagerContent
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface Api {
    @GET("discovery/categories")
    fun getCategories():Call<Categories>
    @GET
    fun getHomePageContent(@Url url:String):Call<HomePagerContent>
}
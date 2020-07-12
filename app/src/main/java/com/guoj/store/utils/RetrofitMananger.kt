package com.guoj.store.utils

import com.guoj.store.model.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Constructor

class RetrofitMananger {
    private var mRetrofit: Retrofit? = null

    private constructor() {
        mRetrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.sunofbeach.net/shop/")
            .build()
    }

    companion object {
        var instance: RetrofitMananger = InstanceHolder.instance
    }
    private object InstanceHolder{
        val instance=RetrofitMananger()
    }

    fun getApi(): Api = mRetrofit?.create(Api::class.java)!!

}

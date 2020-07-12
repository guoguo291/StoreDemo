package com.guoj.store.presenter.impl

import android.util.Log
import com.guoj.store.model.bean.Categories
import com.guoj.store.presenter.IHomePresenter
import com.guoj.store.utils.LogUtils
import com.guoj.store.utils.RetrofitMananger
import com.guoj.store.view.IHomeCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class HomePresenterImpl : IHomePresenter<IHomeCallback> {
    var mhomeCallback: IHomeCallback? = null
    override fun getCategories() {

        val api = RetrofitMananger.instance.getApi()
        val call = api.getCategories()
        call.enqueue(object : Callback<Categories> {
            override fun onFailure(call: Call<Categories>, t: Throwable) {
                LogUtils.i("guoj", t.toString())
                Log.i("ggg", "onFailure: " + t.toString())
            }

            override fun onResponse(call: Call<Categories>, response: Response<Categories>) {
                LogUtils.i("guoj", response.body().toString())
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    val categories = response.body()
                    mhomeCallback?.onCategoriesLoaded(categories)
                }
            }

        })


    }

    override fun registerViewCallback(callback: IHomeCallback) {
        this.mhomeCallback = callback
    }

    override fun unregisterViewCallback(callback: IHomeCallback) {
        mhomeCallback = null
    }
}


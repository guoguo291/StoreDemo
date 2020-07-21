package com.guoj.store.presenter.impl

import com.guoj.store.model.bean.HomePagerContent
import com.guoj.store.presenter.ICategoryPagerPresenter
import com.guoj.store.utils.LogUtils
import com.guoj.store.utils.RetrofitMananger
import com.guoj.store.utils.UrlUtils
import com.guoj.store.view.ICategoryPagerCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.HTTP
import java.net.HttpURLConnection

private const val DEFAULT_PAGE_ID=1
class CategoryPagerPresenterImpl private constructor():ICategoryPagerPresenter {
    companion object {
        var instance:CategoryPagerPresenterImpl=InstanceHolder.instance
    }
    private object InstanceHolder{
        val instance=CategoryPagerPresenterImpl()
    }
    override fun getHomecontentByCategoryId(id:String?) {
        val url=UrlUtils.createHomePageUrl(id, DEFAULT_PAGE_ID)
        var call=RetrofitMananger.instance.getApi().getHomePageContent(url);
        call.enqueue(object :Callback<HomePagerContent> {
            override fun onFailure(call: Call<HomePagerContent>, t: Throwable) {
                LogUtils.i("guoj",t.toString())
            }

            override fun onResponse(
                call: Call<HomePagerContent>,
                response: Response<HomePagerContent>
            ) {
                val respCode=response.code()
                if (respCode==HttpURLConnection.HTTP_OK){
                    var homePagerContent=response.body()
                    LogUtils.i("guoj","onResponse--->${homePagerContent.toString()}")
                }
            }

        })
    }
    private var mCallback:ICategoryPagerCallback?=null
    override fun registerViewCallback(callback: ICategoryPagerCallback) {
        mCallback=callback
    }

    override fun unregisterViewCallback(callback: ICategoryPagerCallback) {
        mCallback=null
    }
}
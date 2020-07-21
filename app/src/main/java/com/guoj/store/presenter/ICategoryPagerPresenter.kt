package com.guoj.store.presenter

import com.guoj.store.base.IBasePresenter
import com.guoj.store.model.bean.HomePagerContent
import com.guoj.store.view.ICategoryPagerCallback

public interface ICategoryPagerPresenter: IBasePresenter<ICategoryPagerCallback>{
   fun getHomecontentByCategoryId(id:String?)

}
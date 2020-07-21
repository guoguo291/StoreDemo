package com.guoj.store.ui.fragment

import android.os.Bundle
import com.guoj.store.R
import com.guoj.store.base.BaseFragment
import com.guoj.store.model.bean.Categories
import com.guoj.store.model.bean.HomePagerContent
import com.guoj.store.presenter.impl.CategoryPagerPresenterImpl
import com.guoj.store.utils.Constants
import com.guoj.store.utils.Constants.ID_MATERIAL
import com.guoj.store.utils.Constants.TITLE
import com.guoj.store.utils.LogUtils
import com.guoj.store.view.ICategoryPagerCallback

class HomePagerFragment : BaseFragment(),ICategoryPagerCallback {
    private var id: String? = null
    private var title: String? = null

    override fun getRootViewResId(): Int=R.layout.fragment_home_pager
    var mCategoryPagerPresenter:CategoryPagerPresenterImpl?=null
    override fun initPresenter() {
        mCategoryPagerPresenter=CategoryPagerPresenterImpl.instance
        mCategoryPagerPresenter!!.registerViewCallback(this)
    }
    override fun loadData() {
        arguments?.let {
            id = it.getString(Constants.ID_MATERIAL)
            title = it.getString(Constants.TITLE)
            LogUtils.i("guoj","id--->$id")
            LogUtils.i("guoj","title--->$title")
        }
        mCategoryPagerPresenter?.getHomecontentByCategoryId(id)
    }
    companion object {
        @JvmStatic
        fun newInstance(categories: Categories.Data) =
            HomePagerFragment().apply {
                arguments = Bundle().apply {
                    putString(ID_MATERIAL, categories.id.toString())
                    putString(TITLE, categories.title)
                }
            }
    }

    override fun onHomeContentLoaded(homePagerContent: HomePagerContent?) {

    }

    override fun onError() {

    }

    override fun onEmpty() {

    }

    override fun onLoading() {

    }
}
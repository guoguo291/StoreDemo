package com.guoj.store.ui.fragment

import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import com.google.android.material.tabs.TabLayout
import com.guoj.store.R
import com.guoj.store.base.BaseFragment
import com.guoj.store.model.bean.Categories
import com.guoj.store.presenter.IHomePresenter
import com.guoj.store.presenter.impl.HomePresenterImpl
import com.guoj.store.ui.adpter.HomePageAdapter
import com.guoj.store.utils.LogUtils
import com.guoj.store.view.IHomeCallback

class HomeFragment : BaseFragment(), IHomeCallback {
    @BindView(R.id.home_indicator)
    lateinit var homeIndicator: TabLayout
    @BindView(R.id.home_pager)
    lateinit var homepager:ViewPager
    override fun getRootViewResId(): Int = R.layout.fragment_home
    lateinit var adapter: HomePageAdapter
    override fun initView() {
        adapter= HomePageAdapter(childFragmentManager)
        homepager.adapter = adapter
        homeIndicator.setupWithViewPager(homepager)
    }

    lateinit var homePresenter: IHomePresenter<IHomeCallback>
    override fun initPresenter() {
        //创建Presenter
        homePresenter = HomePresenterImpl()
        homePresenter.registerViewCallback(this)
    }

    override fun loadData() {
        homePresenter.getCategories()
    }

    override fun onCategoriesLoaded(categories: Categories?) {
        LogUtils.i(this, categories.toString())
        //设置数据
        adapter.setCategoryList(categories)

    }

    override fun release() {
        homePresenter.unregisterViewCallback(this)
    }

}
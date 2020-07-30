package com.guoj.store.ui.fragment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.guoj.store.R
import com.guoj.store.base.BaseFragment
import com.guoj.store.model.bean.Categories
import com.guoj.store.model.bean.Data
import com.guoj.store.model.bean.HomePagerContent
import com.guoj.store.presenter.impl.CategoryPagerPresenterImpl
import com.guoj.store.ui.adpter.LinearItemContentAdapter
import com.guoj.store.utils.Constants
import com.guoj.store.utils.Constants.ID_MATERIAL
import com.guoj.store.utils.Constants.TITLE
import com.guoj.store.utils.LogUtils
import com.guoj.store.view.ICategoryPagerCallback

class HomePagerFragment : BaseFragment(), ICategoryPagerCallback {
    private var id: String? = null
    private var title: String? = null

    @BindView(R.id.home_pager_content_list)
    lateinit var mContentList: RecyclerView
    override fun getRootViewResId(): Int = R.layout.fragment_home_pager

    var mCategoryPagerPresenter: CategoryPagerPresenterImpl? = null
    override fun initPresenter() {
        mCategoryPagerPresenter = CategoryPagerPresenterImpl.instance
        mCategoryPagerPresenter!!.registerViewCallback(this)
    }

    lateinit var linearItemContentAdapter: LinearItemContentAdapter
    override fun initView() {
        linearItemContentAdapter = LinearItemContentAdapter()
        mContentList.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = linearItemContentAdapter
        }
        LogUtils.i("guojj",mContentList.toString())
    }

    override fun loadData() {
        arguments?.let {
            id = it.getString(Constants.ID_MATERIAL)
            title = it.getString(Constants.TITLE)
            LogUtils.i("guoj", "id--->$id")
            LogUtils.i("guoj", "title--->$title")
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

    override fun onHomeContentLoaded(homePagerContents: List<Data>?) {
        linearItemContentAdapter.setData(homePagerContents!!)
        setUpViewByState(ViewState.SUCCESS)
    }

    override fun getCategoryId(): String? {
        return id
    }

    override fun onError() {
        setUpViewByState(ViewState.ERROR)
    }

    override fun onEmpty() {
        setUpViewByState(ViewState.EMPTY)
    }

    override fun onLoading() {
        setUpViewByState(ViewState.LOADING)
    }
}
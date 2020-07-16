package com.guoj.store.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.guoj.store.R

abstract class BaseFragment : Fragment() {
    enum class ViewState {
        NONE, LOADING, ERROR, EMPTY, SUCCESS
    }

    private var bind: Unbinder? = null
    private var baseContainerView:FrameLayout?=null
    @OnClick(R.id.network_error_tips)
    fun retry() {
        //错误后响应点击重新加载
        onRetryClick()
    }
    /**
     * 子fragment网络错误以后的响应点击，可以重写该方法
     */
    open protected fun onRetryClick() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = loadRootView(inflater, container)
        baseContainerView = rootView?.findViewById<FrameLayout>(R.id.base_container)
        loadStateView(inflater, container, baseContainerView)
        bind = ButterKnife.bind(this, rootView!!)
        initView()
        initPresenter()
        loadData()
        return rootView
    }

    /**
     * 通过不同的状态加载view
     */
    private var mSuccessView: View? = null
    private var mErrorView: View? = null
    private var mEmptyView: View? = null
    private var mLoadingView: View? = null
    private fun loadStateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        baseContainerView: ViewGroup?
    ) {
        mSuccessView = loadSuccessView(inflater, container)
        baseContainerView?.addView(mSuccessView)
        mErrorView = loadErrorView(inflater, container)
        baseContainerView?.addView(mErrorView)
        mEmptyView = loadEmptySuccessView(inflater, container)
        baseContainerView?.addView(mEmptyView)
        mLoadingView = loadLoadingView(inflater, container)
        baseContainerView?.addView(mLoadingView)
        setUpViewByState(ViewState.NONE)
    }

    protected fun setUpViewByState(state: ViewState) {
        mSuccessView?.visibility = if (state == ViewState.SUCCESS) View.VISIBLE else View.GONE
        mErrorView?.visibility = if (state == ViewState.ERROR) View.VISIBLE else View.GONE
        mEmptyView?.visibility = if (state == ViewState.EMPTY) View.VISIBLE else View.GONE
        mLoadingView?.visibility = if (state == ViewState.LOADING) View.VISIBLE else View.GONE
        if (state==ViewState.NONE){
            mSuccessView?.visibility=View.GONE
            mErrorView?.visibility=View.GONE
            mEmptyView?.visibility=View.GONE
            mLoadingView?.visibility=View.GONE
        }
    }

    private fun loadLoadingView(inflater: LayoutInflater, container: ViewGroup?): View? {
        return inflater.inflate(R.layout.fragment_loading, container, false)
    }

    private fun loadEmptySuccessView(inflater: LayoutInflater, container: ViewGroup?): View? {
        return inflater.inflate(R.layout.fragment_empty, container, false)
    }

    private fun loadErrorView(inflater: LayoutInflater, container: ViewGroup?): View? {
        return inflater.inflate(R.layout.fragment_error, container, false)
    }


    open protected fun initView() {

    }

    //加载数据
    open protected fun loadData() {

    }

    open protected fun loadRootView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): View? {

        return inflater.inflate(R.layout.fragment_base, container, false)
    }

    protected fun loadSuccessView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): View? {
        return inflater.inflate(getRootViewResId(), container, false)
    }

    abstract fun getRootViewResId(): Int

    open protected fun initPresenter() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        release()
    }

    open fun release() {

    }
}
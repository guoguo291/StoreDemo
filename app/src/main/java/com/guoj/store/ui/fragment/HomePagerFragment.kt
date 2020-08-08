package com.guoj.store.ui.fragment

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import com.guoj.store.R
import com.guoj.store.base.BaseFragment
import com.guoj.store.model.bean.Categories
import com.guoj.store.model.bean.Data
import com.guoj.store.presenter.impl.CategoryPagerPresenterImpl
import com.guoj.store.ui.adpter.LinearItemContentAdapter
import com.guoj.store.ui.adpter.LooperAdapter
import com.guoj.store.utils.Constants.ID_MATERIAL
import com.guoj.store.utils.Constants.TITLE
import com.guoj.store.utils.LogUtils
import com.guoj.store.utils.Utils
import com.guoj.store.view.ICategoryPagerCallback

class HomePagerFragment : BaseFragment(), ICategoryPagerCallback {
    private var id: String? = null
    private var title: String? = null

    @BindView(R.id.home_pager_content_list)
    lateinit var mContentList: RecyclerView

    @BindView(R.id.home_vp_looper)
    lateinit var mLooper: ViewPager

    @BindView(R.id.home_pager_title)
    lateinit var currentCategoryTitleTv: TextView

    @BindView(R.id.looper_point_container)
    lateinit var looperPointContainer: LinearLayout


    override fun getRootViewResId(): Int = R.layout.fragment_home_pager

    private var mCategoryPagerPresenter: CategoryPagerPresenterImpl? = null
    override fun initPresenter() {
        mCategoryPagerPresenter = CategoryPagerPresenterImpl.instance
        mCategoryPagerPresenter!!.registerViewCallback(this)
    }

    private lateinit var linearItemContentAdapter: LinearItemContentAdapter
    private lateinit var looperAdapter: LooperAdapter
    override fun initView() {
        linearItemContentAdapter = LinearItemContentAdapter()
        mContentList.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = linearItemContentAdapter
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    super.getItemOffsets(outRect, view, parent, state)
                    outRect.top = Utils.dip2pix(context, 1.5f)
                    outRect.bottom = Utils.dip2pix(context, 1.5f)
//                    outRect.set(10,5,10,5)
                }
                /**
                 *绘制背景色
                 */
//                override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
//                    super.onDraw(c, parent, state)
//                    val paint = Paint()
//                    paint.color=Color.RED
//                    c.drawRect(0F, 0F, parent.width.toFloat(),parent.height.toFloat(),paint)
//                }
            })
        }
        LogUtils.i("guojj", mContentList.toString())
        //初始化轮播图
        looperAdapter = LooperAdapter()
        mLooper.apply {
            adapter = looperAdapter
//            offscreenPageLimit=3//设置预加载页数
        }
    }

    override fun initListener() {
        mLooper.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                if (looperAdapter.getDataSize() == 0) return
                val targetPositon = position % looperAdapter.getDataSize()
                //指示器显示切换
                updateLooperIndicator(targetPositon)
            }

        })
    }

    private fun updateLooperIndicator(positon: Int) {
        for (i in 0 until looperPointContainer.childCount) {
            val pointView = looperPointContainer.getChildAt(i)
            if (i == positon) {
                pointView.setBackgroundResource(R.drawable.shape_indicator_point_selected)
            }else{
                pointView.setBackgroundResource(R.drawable.shape_indicator_point_normal)
            }
        }
    }

    override fun loadData() {
        arguments?.let {
            id = it.getString(ID_MATERIAL)
            title = it.getString(TITLE)
            LogUtils.i("guoj", "id--->$id")
            LogUtils.i("guoj", "title--->$title")
        }
        if (title != null) {
            currentCategoryTitleTv.text = title
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

    override fun onLooperLoaded(homePagerContents: List<Data>?) {
        val dx = (Int.MAX_VALUE / 2) % homePagerContents!!.size
        mLooper.currentItem = Int.MAX_VALUE / 2 - dx
        looperAdapter.setData(homePagerContents)
        //加载点
        looperPointContainer.removeAllViews()
        for (i in homePagerContents.indices) {
            val point = View(context)
            val viewSize = Utils.dip2pix(requireContext(), 8f)
            val layoutParams = LinearLayout.LayoutParams(viewSize, viewSize)
            layoutParams.apply {
                leftMargin = Utils.dip2pix(requireContext(), 5f)
                rightMargin = Utils.dip2pix(requireContext(), 5f)
            }
            point.layoutParams = layoutParams
            point.setBackgroundResource(if (i == 0) R.drawable.shape_indicator_point_selected else R.drawable.shape_indicator_point_normal)
            looperPointContainer.addView(point)
        }
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
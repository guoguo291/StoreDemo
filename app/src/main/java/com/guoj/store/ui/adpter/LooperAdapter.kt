package com.guoj.store.ui.adpter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.guoj.store.model.bean.Data
import com.guoj.store.utils.LogUtils
import com.guoj.store.utils.UrlUtils

class LooperAdapter : PagerAdapter() {
    var mDatas = ArrayList<Data>()
    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int {
//        return if (mDatas.size > 0) mDatas.size else 0
        //无限轮播
        return Int.MAX_VALUE
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(container.context)
        val layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        imageView.apply {
            this.layoutParams=layoutParams
            this.scaleType=ImageView.ScaleType.CENTER_CROP
        }
        //无限轮播下标是无限的，根据实际数据获取真正的下标
        val  realPosition= position % mDatas.size
        val measuredHeight = container.measuredHeight
        val measuredWidth = container.measuredWidth
        val ivSize =
            (if (measuredWidth > measuredHeight) measuredWidth else measuredHeight) / 2
        val pictUrl = UrlUtils.getCoverPath(mDatas.get(realPosition).pict_url,ivSize)
        Glide.with(container.context).load(pictUrl).into(imageView)
        container.addView(imageView)
        return imageView
    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }
    fun setData(homePagerContents: List<Data>?) {
        if (homePagerContents != null) {
            LogUtils.i("looper", "setData:===== ")
            mDatas.clear()
            mDatas.addAll(homePagerContents)
            notifyDataSetChanged()
        }
    }

}



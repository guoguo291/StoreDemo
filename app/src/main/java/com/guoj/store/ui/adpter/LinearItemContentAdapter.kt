package com.guoj.store.ui.adpter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.guoj.store.R
import com.guoj.store.model.bean.Data
import com.guoj.store.model.bean.HomePagerContent
import com.guoj.store.utils.LogUtils
import com.guoj.store.utils.UrlUtils

class LinearItemContentAdapter : Adapter<LinearItemContentAdapter.InnerHolder>() {
    private val contentList: ArrayList<Data> = ArrayList()
    override fun getItemCount(): Int {
        return contentList.size
    }

    override fun onBindViewHolder(holder: InnerHolder, position: Int) {
        val data = contentList.get(position)
        holder.setData(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerHolder {
        var itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_linear_goods_content, parent, false)
        return InnerHolder(itemView)
    }

    class InnerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            ButterKnife.bind(this, itemView)
        }

        @BindView(R.id.goods_cover)
        lateinit var coverIv: ImageView

        @BindView(R.id.goods_title)
        lateinit var title: TextView

        @BindView(R.id.goods_off_prise)
        lateinit var offPriseTv: TextView

        @BindView(R.id.goods_after_off_prise)
        lateinit var finalPriseTv: TextView

        @BindView(R.id.goods_original_prise)
        lateinit var originalPriseTv: TextView

        @BindView(R.id.goods_sell_count)
        lateinit var sellCountTv: TextView
        fun setData(data: Data) {
            val context=itemView.context
            title.text = data.title
            if (data.pict_url.isNotEmpty()) {
                val pic_url = UrlUtils.getCoverPath(data.pict_url)
                Glide.with(context).load(pic_url).into(coverIv)
            } else {
                coverIv.setImageResource(R.mipmap.ic_launcher)
            }
            val finalPrise = data.zk_final_price
            val couponAmount = data.coupon_amount
            val resultPrise=finalPrise.toFloat()-couponAmount
            finalPriseTv.text = String.format("%.2f",resultPrise)
            offPriseTv.text = String.format(context.getString(R.string.text_goods_off_prise),couponAmount)
            originalPriseTv.apply {
                paintFlags=Paint.STRIKE_THRU_TEXT_FLAG
                text=String.format(context.getString(R.string.text_goods_original_prise),finalPrise)
            }
            sellCountTv.text=String.format(context.getString(R.string.text_goods_sell_count),data.volume)
        }

    }

    fun setData(contents: List<Data>) {
        contentList.clear()
        contentList.addAll(contents)
        notifyDataSetChanged()
        LogUtils.i("guojj", "setData:" + contents.toString())
    }
}
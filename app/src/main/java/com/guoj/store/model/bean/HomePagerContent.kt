package com.guoj.store.model.bean

data class HomePagerContent(
    val code: Long,
    val `data`: List<Data>,
    val message: String,
    val success: Boolean

) {
    override fun toString(): String {
        return "HomePagerContent(code=$code, `data`=$`data`, message='$message', success=$success)"
    }
}

data class Data(
    val category_id: Long,
    val category_name: Any,
    val click_url: String,
    val commission_rate: String,
    val coupon_amount: Long,
    val coupon_click_url: String,
    val coupon_end_time: String,
    val coupon_info: Any,
    val coupon_remain_count: Long,
    val coupon_share_url: String,
    val coupon_start_fee: String,
    val coupon_start_time: String,
    val coupon_total_count: Long,
    val item_description: String,
    val item_id: Long,
    val level_one_category_id: Long,
    val level_one_category_name: String,
    val nick: String,
    val pict_url: String,
    val seller_id: Long,
    val shop_title: String,
    val small_images: SmallImages,
    val title: String,
    val user_type: Long,
    val volume: Long,
    val zk_final_price: String

) {
    override fun toString(): String {
        return "Data(category_id=$category_id, category_name=$category_name, click_url='$click_url', commission_rate='$commission_rate', coupon_amount=$coupon_amount, coupon_click_url='$coupon_click_url', coupon_end_time='$coupon_end_time', coupon_info=$coupon_info, coupon_remain_count=$coupon_remain_count, coupon_share_url='$coupon_share_url', coupon_start_fee='$coupon_start_fee', coupon_start_time='$coupon_start_time', coupon_total_count=$coupon_total_count, item_description='$item_description', item_id=$item_id, level_one_category_id=$level_one_category_id, level_one_category_name='$level_one_category_name', nick='$nick', pict_url='$pict_url', seller_id=$seller_id, shop_title='$shop_title', small_images=$small_images, title='$title', user_type=$user_type, volume=$volume, zk_final_price='$zk_final_price')"
    }
}

data class SmallImages(
    val string: List<String>

) {
    override fun toString(): String {
        return "SmallImages(string=$string)"
    }
}
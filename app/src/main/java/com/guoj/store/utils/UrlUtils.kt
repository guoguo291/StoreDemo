package com.guoj.store.utils

object UrlUtils {
    fun createHomePageUrl(materialId:String?,page:Int):String{
        return "discovery/" + materialId + "/" + page
    }
    fun getCoverPath(pict_url: String): String {
        return if (pict_url.startsWith("http") || pict_url.startsWith("https")) pict_url else "https:$pict_url"
    }
}
package com.guoj.store.utils

object UrlUtils {
    fun createHomePageUrl(materialId:String?,page:Int):String{
        return "discovery/" + materialId + "/" + page
    }
}
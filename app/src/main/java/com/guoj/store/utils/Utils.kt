package com.guoj.store.utils

import android.content.Context
import android.graphics.Point
import android.view.WindowManager




object Utils {
    fun getWindowsWidth(context: Context):Int{
        val wm = context
            .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val point = Point()
        wm.defaultDisplay.getSize(point)
        val width = point.x
        val height = point.y
        return width
    }
    fun getWindowsHeight(context: Context):Int{
        val wm = context
            .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val point = Point()
        wm.defaultDisplay.getSize(point)
        val height = point.y
        return height
    }
    fun dip2pix(context: Context,dpValue:Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
}
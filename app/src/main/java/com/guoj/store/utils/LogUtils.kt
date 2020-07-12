package com.guoj.store.utils

import android.util.Log

object LogUtils {
    private const val currentLev = 4
    private const val DEBUG_LEV = 4
    private const val INFO_LEV = 3
    private const val WARNING_LEV = 2
    private const val ERROR_LEV = 1
    fun d(tag: String, log: String?) {
        if (currentLev >= DEBUG_LEV) {
            Log.d(tag, "$log")
        }
    }
    fun i(tag: Any, log: String?) {
        if (currentLev >= INFO_LEV) {
//            Log.i(tag, "$log")
            Log.i(tag.javaClass.name, "$log")
        }
    }
     fun w(tag: String, log: String?) {
        if (currentLev >= WARNING_LEV) {
            Log.w(tag, "$log")
        }
    }
     fun e(tag: String, log: String?) {
        if (currentLev >= ERROR_LEV) {
            Log.e(tag, "$log")
        }
    }


}
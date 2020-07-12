package com.guoj.store.base

interface IBasePresenter<T> {
    /**
     * 注册UI通知监听
     */
    fun registerViewCallback(callback:T)
    /**
     * 取消UI通知监听
     */
    fun unregisterViewCallback(callback:T)
}
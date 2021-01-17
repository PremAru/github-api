package com.travello.github.base

import java.lang.ref.WeakReference

open class BasePresenter<V> {
    lateinit var view : WeakReference<V>

    fun getView() : V {
        return view.get()!!
    }

    fun setView(view : V){
        this.view = WeakReference(view)
    }

}
package com.one.kotlinlesson

/**
 * @author  diaokaibin@gmail.com on 2020/3/31.
 */
interface BaseView<T> {
    fun getPresenter(): T
}
package com.one.core.http

/**
 * @author  diaokaibin@gmail.com on 2020/3/31.
 */
interface EntityCallback<T> {
    fun onSuccess(entity: T)

    fun onFailure(message: String?)
}
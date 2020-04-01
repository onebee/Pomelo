package com.one.kotlinlesson.utils

import android.content.Context
import com.one.kotlinlesson.BaseApplication
import com.one.kotlinlesson.R

/**
 * @author  diaokaibin@gmail.com on 2020/3/31.
 */
object CacheUtils {

    val context = BaseApplication.currentApplication()
    val sp = context.getSharedPreferences(
        context.getString(R.string.app_name),
        Context.MODE_PRIVATE
    )


    fun save(key: String?, value: String?) {
        sp.edit().putString(key, value).apply()
    }

    fun get(key: String?): String? {
        return sp.getString(key, null)
    }


}
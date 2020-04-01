package com.one.kotlinlesson.http

import android.util.Log
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.one.core.http.EntityCallback
import okhttp3.*
import java.io.IOException
import java.lang.reflect.Type

/**
 * @author  diaokaibin@gmail.com on 2020/3/31.
 *
 */
object HttpClient : OkHttpClient() {  // 里面没有构造,所以在这里加上括号
    private val gson = Gson()
    private val builder = Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .build()

    private fun <T> convert(json: String?, type: Type): T {
        return gson.fromJson(json, type)
    }


    fun <T> get(path: String?, type: Type, entityCallback: EntityCallback<T>) {

        val request = Request.Builder().url("https://api.hencoder.com/$path").build()
        builder.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                entityCallback.onFailure("网络异常")
                Log.e("------", e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                when (response.code) {
                    in 200..299 -> {
                        val body = response.body
                        val json: String?
                        json = body!!.string()
                        entityCallback.onSuccess(convert(json, type) as T)
                    }
                    in 400..499 -> {
                        entityCallback.onFailure("客户端错误")
                    }
                    in 500..599 -> {
                        entityCallback.onFailure("服务端错误")
                    }
                    else -> {
                        entityCallback.onFailure("未知错误")
                    }
                }
            }

        })

    }


}

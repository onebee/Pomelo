package com.one.kotlinlesson

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho

/**
 * @author  diaokaibin@gmail.com on 2020/3/31.
 */
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this);
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        currentApplication = this;
    }

    companion object {
        @get:JvmName("currentApplication")
        @JvmStatic
         lateinit var currentApplication: Context
            private set
//        fun currentApplication(): Context {
//            return currentApplication
//        }
    }
}
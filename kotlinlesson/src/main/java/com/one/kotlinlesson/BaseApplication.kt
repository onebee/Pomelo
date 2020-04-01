package com.one.kotlinlesson

import android.app.Application
import android.content.Context

/**
 * @author  diaokaibin@gmail.com on 2020/3/31.
 */
class BaseApplication : Application() {


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        currentApplication = this;
    }

    companion object {
        private lateinit var currentApplication: Context

        fun currentApplication(): Context {
            return currentApplication;
        }
    }

}
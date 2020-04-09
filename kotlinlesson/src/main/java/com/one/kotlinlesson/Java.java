package com.one.kotlinlesson;

import android.widget.Toast;

import com.one.kotlinlesson.utils.Utils;

/**
 * @author diaokaibin@gmail.com on 2020/4/6.
 */
public class Java {

    void fun() {
        BaseApplication.currentApplication();
        Utils.toash("A");
        Utils.toash("A", Toast.LENGTH_LONG);
    }


}

package com.one.touchsample;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * @author diaokaibin@gmail.com on 2019-12-18.
 */
public class MyViewGroupA extends ViewGroup {
    public MyViewGroupA(Context context) {
        super(context);
    }

    public MyViewGroupA(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroupA(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Log.d("xyz", "MyViewGroupA - onTouchEvent : " + event.getAction());
        return super.onTouchEvent(event);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("xyz", "MyViewGroupA - dispatchTouchEvent : " + event.getAction());
        return super.dispatchTouchEvent(event);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Log.d("xyz", "MyViewGroupA - onInterceptTouchEvent : " + event.getAction());
        return super.onInterceptTouchEvent(event);
    }

}

package com.hand.a28_lib_reflection;

import android.app.Activity;

import java.lang.reflect.Field;

/**
 * @author diaokaibin@gmail.com on 2019/2/27.
 */
public class Binding {

    public static void bind(Activity activity) {
        for (Field field : activity.getClass().getDeclaredFields()) {
            BindView bindView = field.getAnnotation(BindView.class);
            if (bindView != null) {
                try {
                    field.setAccessible(true);
                    field.set(activity,activity.findViewById(bindView.value()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

package com.hand.a28_annation_processing;

import android.app.Activity;

/**
 * @author diaokaibin@gmail.com on 2019/2/27.
 */
public class Binding {

    public static void bind(Activity activity) {

        try {
            Class bindingClass = Class.forName(activity.getClass().getCanonicalName()+"Binding");
            Class activClass = Class.forName(activity.getClass().getCanonicalName());


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

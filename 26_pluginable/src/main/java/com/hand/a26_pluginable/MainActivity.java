package com.hand.a26_pluginable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Class clazz = Class.forName("com.hand.a26_pluginable.hidden.Util");
            Constructor constructor = clazz.getDeclaredConstructors()[0];
            constructor.setAccessible(true);
            Object instance = constructor.newInstance();
            Method method = clazz.getDeclaredMethod("shout");
            method.setAccessible(true);
            method.invoke(instance);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

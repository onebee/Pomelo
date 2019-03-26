package com.hand.a26_pluginable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

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


        // 拷贝assets 文件下面 放到 该应用下面的cache 目录
        File apk = new File(getCacheDir() + "/26_pluginable_plug-debug.apk");
        if (!apk.exists()) {
            try {
                InputStream inputStream = getAssets().open("apk/26_pluginable_plug-debug.apk");
                int availableSize = inputStream.available();
                byte[] buffer = new byte[availableSize];
                inputStream.read(buffer);
                inputStream.close();

                FileOutputStream outputStream = new FileOutputStream(apk);
                outputStream.write(buffer);
                outputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            DexClassLoader dexClassLoader = new DexClassLoader(apk.getPath(), getCacheDir().getPath(), null, null);
            Class plugClass = dexClassLoader.loadClass("com.hand.a26_pluginable_plug.Util");
            Constructor constructor = plugClass.getDeclaredConstructors()[0];
//            constructor.setAccessible(true);
            Object instance = constructor.newInstance();
            Method method = plugClass.getDeclaredMethod("shout");
//            method.setAccessible(true);
            method.invoke(instance);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}

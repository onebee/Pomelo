package com.one.rxjavasample;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      TextView tv =  findViewById(R.id.tv);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        api.listRepos("onebee")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Repo>>() {
            @Override
            public void onSubscribe(Disposable d) {
                tv.setText("loading");
            }

            @Override
            public void onSuccess(List<Repo> repos) {
                tv.setText(repos.get(0).fullName);
            }

            @Override
            public void onError(Throwable e) {

                String error = e.getMessage();
                if (error == null) {
                    error = e.getClass().getName();
                }

                tv.setText(error);
            }
        });
    }
}

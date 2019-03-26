package com.hand.a28_annation_processing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    // 默认的value 方法 可以简写
//    @BindView(R.id.tv)
//    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Binding.bind(this);
//
//        mTextView.setText("one bit ");


    }
}

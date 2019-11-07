package com.hand.a27_hot_update;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button orign = (Button) findViewById(R.id.btn_origin);
        Button hot = (Button) findViewById(R.id.btn_hot);
        final TextView tv = findViewById(R.id.tv);


        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.btn_hot:
                        tv.setText("i'm origin");

                        break;

                    case R.id.btn_origin:
//                        System.out.println(getClassLoader().getClass().getName());

                        break;
                }

            }
        };

        orign.setOnClickListener(listener);
        hot.setOnClickListener(listener);

    }
}

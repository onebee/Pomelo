package com.cheeath.pomelo;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cheeath.pomelo.view.Dashboard;

public class MainActivity extends AppCompatActivity {


    private int angle = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Dashboard view = findViewById(R.id.view);

        CountDownTimer countDownTimer = new CountDownTimer(30000000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                angle = angle + 6;
                view.setRoate(angle);
            }

            @Override
            public void onFinish() {

            }
        };
        countDownTimer.start();
    }
}

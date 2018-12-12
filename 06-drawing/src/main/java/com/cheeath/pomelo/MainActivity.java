package com.cheeath.pomelo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cheeath.pomelo.view.ClockView;

public class MainActivity extends AppCompatActivity {


    private int angle = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ClockView view = findViewById(R.id.view);

//        CountDownTimer countDownTimer = new CountDownTimer(30000000, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                angle = angle + 6;
//                view.setSecondHand(angle);
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        };
//        countDownTimer.start();
    }
}

package com.cheeath.pomelo;

import android.arch.lifecycle.MutableLiveData;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    private int angle = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        final ClockView view = findViewById(R.id.view);

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

        Calendar instance = Calendar.getInstance();
        Date today = new Date();
        Date tomorrow = getDateAfter(today, 1);
        Date dayAfterTomorrow = getDateAfter(today, 2);

        MutableLiveData<Date> mutableLiveData = new MutableLiveData<>();


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-EEE", Locale.ENGLISH);
        String toadyS = sdf.format(today);
        String tomorrowS = sdf.format(tomorrow);
        String dayAfterTomorrowS = sdf.format(dayAfterTomorrow);

//        TextView tv = findViewById(R.id.tv);
//        tv.setText(toadyS + "\n"
//
//                + tomorrowS + "\n"
//
//                + dayAfterTomorrowS + "\n"
//        );

    }

    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }
}

package one.one.com.a08_animation;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View view = findViewById(R.id.view);

        ObjectAnimator animator = new ObjectAnimator();
        view.animate()
                .translationX(Utils.dp2px(200))
//                .translationY(100)
//                .rotation(180)
                .setStartDelay(2000)
                .start();


    }
}

package one.one.com.a08_animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CameraView view = findViewById(R.id.view);

//        ObjectAnimator animator = ObjectAnimator.ofFloat(view,"RADIUS",Utils.dp2px(100));
//
//
//        animator.setStartDelay(2000);
//        animator.start();


//        view.animate()
//                .translationX(Utils.dp2px(200))
//                .translationY(100)
//                .rotation(180)
//                .setStartDelay(2000)
//                .start();

        ObjectAnimator bottomAnimator = ObjectAnimator.ofFloat(view,"bottomFlip",45);

//        animator.setStartDelay(1000);
        bottomAnimator.setDuration(1500);
//        animator.start();

        ObjectAnimator topAnimator = ObjectAnimator.ofFloat(view,"topFlip",-45);
        topAnimator.setDuration(1500);

        ObjectAnimator flipRotationAnimator = ObjectAnimator.ofFloat(view,"flipRotation",270);
        flipRotationAnimator.setDuration(1500);

        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.playSequentially(bottomAnimator,flipRotationAnimator,topAnimator);
        animatorSet.setStartDelay(1000);
        animatorSet.start();




    }
}

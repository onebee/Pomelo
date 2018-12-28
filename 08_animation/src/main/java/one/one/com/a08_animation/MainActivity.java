package one.one.com.a08_animation;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VoiceLine view = findViewById(R.id.view);
//        CircleView view1 = findViewById(R.id.view1);

//        ObjectAnimator animator = ObjectAnimator.ofFloat(view1,"RADIUS",Utils.dp2px(100));
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

//        ObjectAnimator bottomAnimator = ObjectAnimator.ofFloat(view,"bottomFlip",45);
//
////        animator.setStartDelay(1000);
//        bottomAnimator.setDuration(1500);
////        animator.start();
//
//        ObjectAnimator topAnimator = ObjectAnimator.ofFloat(view,"topFlip",-45);
//        topAnimator.setDuration(1500);
//
//        ObjectAnimator flipRotationAnimator = ObjectAnimator.ofFloat(view,"flipRotation",270);
//        flipRotationAnimator.setDuration(1500);
//
//        AnimatorSet animatorSet = new AnimatorSet();
//
//        animatorSet.playSequentially(bottomAnimator,flipRotationAnimator,topAnimator);
//        animatorSet.setStartDelay(1000);
//        animatorSet.start();


        // 三个一起  同一个View 多个属性 同时改变
//        PropertyValuesHolder bottomFlipHolder = PropertyValuesHolder.ofFloat("bottomFlip",45);
//        PropertyValuesHolder topFlipHolder = PropertyValuesHolder.ofFloat("topFlip",-45);
//        PropertyValuesHolder flipRotationHolder = PropertyValuesHolder.ofFloat("flipRotation",270);
//
//
//        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view,bottomFlipHolder,topFlipHolder,flipRotationHolder);
//
//        objectAnimator.setStartDelay(1000);
//        objectAnimator.start();


//        float LENGTH = Utils.dp2px(300);
//        Keyframe keyframe1 = Keyframe.ofFloat(0, 0);
//        Keyframe keyframe2 = Keyframe.ofFloat(0.2f, 1.4f * LENGTH);
//        Keyframe keyframe3 = Keyframe.ofFloat(0.8f, 0.6f * LENGTH);
//        Keyframe keyframe4 = Keyframe.ofFloat(1, 1 * LENGTH);
//
//        PropertyValuesHolder holder = PropertyValuesHolder.ofKeyframe("translationX",keyframe1,keyframe2,keyframe3,keyframe4);
//        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view,holder);
//        animator.setDuration(1500);
//        animator.setStartDelay(1000);
//        animator.start();



        float LENGTH = Utils.dp2px(300);
        Keyframe keyframe1 = Keyframe.ofFloat(0, 0);
        Keyframe keyframe2 = Keyframe.ofFloat(0.2f, 1.4f * LENGTH);
        Keyframe keyframe3 = Keyframe.ofFloat(0.8f, 0.6f * LENGTH);
        Keyframe keyframe4 = Keyframe.ofFloat(1f,   1 * LENGTH);
        Keyframe keyframe5 = Keyframe.ofFloat(0.6f, 0.6f * LENGTH);
        Keyframe keyframe6 = Keyframe.ofFloat(0.2f, 0.3f * LENGTH);
        Keyframe keyframe7 = Keyframe.ofFloat(0.1f, 0.1f * LENGTH);
//
        PropertyValuesHolder holder = PropertyValuesHolder.ofKeyframe("lengthX",keyframe1,keyframe2,keyframe3,keyframe4,keyframe5,keyframe6,keyframe7);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view,holder);
        animator.setDuration(5500);
        animator.setStartDelay(1000);
        animator.start();

//        ObjectAnimator animator1 = ObjectAnimator.ofFloat(view,"lengthX",Utils.dp2px(500));
//        animator1.setDuration(2000);
//        animator1.setStartDelay(1000);
//        animator1.start();
//


    }
}

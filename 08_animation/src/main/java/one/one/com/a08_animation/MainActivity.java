package one.one.com.a08_animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private AutoScrollTextView auto_tv;


    private String s1 = "Hi, 主人您好!";
    private String s2 = "欢迎您使用科勒镜柜";
    private String s3 = "下面镜子将进入初始化阶段";
    private String s4 = "请选择您的网络并连接";
    private HaView tv1, tv2, tv3, tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        VoiceLine view = findViewById(R.id.view);
//
//
//
//        float LENGTH = Utils.dp2px(300);
//        Keyframe keyframe1 = Keyframe.ofFloat(0, 0);
//        Keyframe keyframe2 = Keyframe.ofFloat(0.2f, 1.4f * LENGTH);
//        Keyframe keyframe3 = Keyframe.ofFloat(0.8f, 0.6f * LENGTH);
//        Keyframe keyframe4 = Keyframe.ofFloat(1f,   1 * LENGTH);
//        Keyframe keyframe5 = Keyframe.ofFloat(0.6f, 0.6f * LENGTH);
//        Keyframe keyframe6 = Keyframe.ofFloat(0.2f, 0.3f * LENGTH);
//        Keyframe keyframe7 = Keyframe.ofFloat(0.1f, 0.1f * LENGTH);
////
//        PropertyValuesHolder holder = PropertyValuesHolder.ofKeyframe("lengthX",keyframe1,keyframe2,keyframe3,keyframe4,keyframe5,keyframe6,keyframe7);
//        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view,holder);
//        animator.setDuration(5500);
//        animator.setStartDelay(1000);
//        animator.start();

//        String msg = s1 + "\n" + s2 + '\n' + s3 + '\n' + s4;
//
//        auto_tv = (AutoScrollTextView) findViewById(R.id.auto_textview);
//
//        auto_tv.setText(msg);
//        auto_tv.init(getWindowManager());
//        auto_tv.startScroll();


        long time = 500;
        float size = 5;


        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        float height = Utils.dp2px(50);

        // 初始化
        ObjectAnimator animator0 = ObjectAnimator.ofFloat(tv2, "translationY", height);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(tv3, "translationY", height);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(tv4, "translationY", height);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(10);
        animatorSet.playTogether(animator0, animator1, animator2);
        animatorSet.start();


        // 第一阶段
        ObjectAnimator objectAnimatorTv1Y = ObjectAnimator.ofFloat(tv1, "translationY", -height);
        ObjectAnimator objectAnimatorTv1Size = ObjectAnimator.ofFloat(tv1, "haSize", size);
        ObjectAnimator objectAnimatorTv1Color = ObjectAnimator.ofInt(tv1, "haColor", 5);

        ObjectAnimator objectAnimatorTv2Y = ObjectAnimator.ofFloat(tv2, "translationY", height, 0);

        AnimatorSet animatorSet1 = new AnimatorSet();
        animatorSet1.setDuration(time);
        animatorSet1.playTogether(objectAnimatorTv1Y, objectAnimatorTv1Size, objectAnimatorTv1Color, objectAnimatorTv2Y);
        animatorSet1.start();


        // 第二阶段
        ObjectAnimator objectAnimatorTv1Y2 = ObjectAnimator.ofFloat(tv1, "translationY", -height, -2 * height);
        ObjectAnimator objectAnimatorTv1Size2 = ObjectAnimator.ofFloat(tv1, "haSize", size, 0);
        ObjectAnimator objectAnimatorTv1Color2 = ObjectAnimator.ofInt(tv1, "haColor", 5, 0);

        ObjectAnimator objectAnimatorTv2Y2 = ObjectAnimator.ofFloat(tv2, "translationY", 0, -height);
        ObjectAnimator objectAnimatorTv2Size2 = ObjectAnimator.ofFloat(tv2, "haSize", size);
        ObjectAnimator objectAnimatorTv2Color2 = ObjectAnimator.ofInt(tv2, "haColor", 5);

        ObjectAnimator objectAnimatorTv3Y2 = ObjectAnimator.ofFloat(tv3, "translationY", 0);


        final AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.setDuration(time);


        animatorSet2.playTogether(objectAnimatorTv1Y2, objectAnimatorTv1Size2, objectAnimatorTv1Color2,
                objectAnimatorTv2Y2, objectAnimatorTv2Size2, objectAnimatorTv2Color2,
                objectAnimatorTv3Y2
        );

        animatorSet1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animatorSet2.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });


        // 第三阶段
        final AnimatorSet animatorSet3 = new AnimatorSet();
        animatorSet3.setDuration(time);

        ObjectAnimator objectAnimatorTv1Y3 = ObjectAnimator.ofFloat(tv1, "translationY", -2 * height, -3 * height);

        ObjectAnimator objectAnimatorTv2Y3 = ObjectAnimator.ofFloat(tv2, "translationY", -height, -2 * height);
        ObjectAnimator objectAnimatorTv2Size3 = ObjectAnimator.ofFloat(tv2, "haSize", size, 0);
        ObjectAnimator objectAnimatorTv2Color3 = ObjectAnimator.ofInt(tv2, "haColor", 5, 0);

        ObjectAnimator objectAnimatorTv3Y3 = ObjectAnimator.ofFloat(tv3, "translationY", 0, -height);
        ObjectAnimator objectAnimatorTv3Size3 = ObjectAnimator.ofFloat(tv3, "haSize", size);
        ObjectAnimator objectAnimatorTv3Color3 = ObjectAnimator.ofInt(tv3, "haColor", 5);

        ObjectAnimator objectAnimatorTv4Y3 = ObjectAnimator.ofFloat(tv4, "translationY", 0);

        animatorSet3.playTogether(objectAnimatorTv2Y3, objectAnimatorTv2Size3, objectAnimatorTv2Color3, objectAnimatorTv1Y3,
                objectAnimatorTv3Y3, objectAnimatorTv3Size3, objectAnimatorTv3Color3, objectAnimatorTv4Y3

        );

        animatorSet2.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animatorSet3.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        // 第四阶段

        final AnimatorSet animatorSet4 = new AnimatorSet();

        animatorSet4.setDuration(time);


        ObjectAnimator objectAnimatorTv1Y4 = ObjectAnimator.ofFloat(tv1, "translationY", -3 * height, -4 * height);

        ObjectAnimator objectAnimatorTv2Y4 = ObjectAnimator.ofFloat(tv2, "translationY", -2*height,-3 * height);

        ObjectAnimator objectAnimatorTv3Y4 = ObjectAnimator.ofFloat(tv3, "translationY", -height,-2 * height);
        ObjectAnimator objectAnimatorTv3Size4 = ObjectAnimator.ofFloat(tv3, "haSize",  0);
        ObjectAnimator objectAnimatorTv3Color4 = ObjectAnimator.ofInt(tv3, "haColor",  0);


        ObjectAnimator objectAnimatorTv4Y4 = ObjectAnimator.ofFloat(tv4, "translationY", 0,-height);
        ObjectAnimator objectAnimatorTv4Size4 = ObjectAnimator.ofFloat(tv4, "haSize", size);
        ObjectAnimator objectAnimatorTv4Color4 = ObjectAnimator.ofInt(tv4, "haColor", 5);

        animatorSet4.playTogether(objectAnimatorTv1Y4,objectAnimatorTv2Y4,objectAnimatorTv3Y4,objectAnimatorTv3Size4,objectAnimatorTv3Color4,
                objectAnimatorTv4Y4,objectAnimatorTv4Size4,objectAnimatorTv4Color4

        );

        animatorSet3.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animatorSet4.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        // 阶段5
        final AnimatorSet animatorSet5 = new AnimatorSet();
        animatorSet5.setDuration(time);
        ObjectAnimator objectAnimatorTv1Y5 = ObjectAnimator.ofFloat(tv1, "translationY", -4 * height, -5 * height);
        ObjectAnimator objectAnimatorTv2Y5 = ObjectAnimator.ofFloat(tv2, "translationY", -3*height,-4 * height);
        ObjectAnimator objectAnimatorTv3Y5 = ObjectAnimator.ofFloat(tv3, "translationY", -2*height,-3 * height);
        ObjectAnimator objectAnimatorTv4Y5 = ObjectAnimator.ofFloat(tv4, "translationY", -height,-2*height);
        ObjectAnimator objectAnimatorTv4Size5 = ObjectAnimator.ofFloat(tv4, "haSize", 0);
        ObjectAnimator objectAnimatorTv4Color5 = ObjectAnimator.ofInt(tv4, "haColor", 0);
        animatorSet5.playTogether(objectAnimatorTv1Y5,objectAnimatorTv2Y5,objectAnimatorTv3Y5,objectAnimatorTv4Y5,objectAnimatorTv4Size5,objectAnimatorTv4Color5);

        animatorSet4.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                animatorSet5.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        // 阶段6
        final AnimatorSet animatorSet6 = new AnimatorSet();
        animatorSet6.setDuration(time);
        ObjectAnimator objectAnimatorTv1Y6 = ObjectAnimator.ofFloat(tv1, "translationY",  -5 * height,-8 *height);
        ObjectAnimator objectAnimatorTv2Y6 = ObjectAnimator.ofFloat(tv2, "translationY", -4 * height,-7*height);
        ObjectAnimator objectAnimatorTv3Y6 = ObjectAnimator.ofFloat(tv3, "translationY", -3 * height,-6 *height);
        ObjectAnimator objectAnimatorTv4Y6 = ObjectAnimator.ofFloat(tv4, "translationY", -2*height,-5*height);

        animatorSet6.playTogether(objectAnimatorTv1Y6,objectAnimatorTv2Y6,objectAnimatorTv3Y6,objectAnimatorTv4Y6);

        animatorSet5.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                animatorSet6.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });





    }
}

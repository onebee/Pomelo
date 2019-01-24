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


        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);

        // 第一阶段
        ObjectAnimator objectAnimatorTv1Y = ObjectAnimator.ofFloat(tv1, "translationY", -Utils.dp2px(50)).setDuration(2000);
        ObjectAnimator objectAnimatorTv1Size = ObjectAnimator.ofFloat(tv1, "haSize", 5).setDuration(2000);
        ObjectAnimator objectAnimatorTv1Color = ObjectAnimator.ofInt(tv1, "haColor", 5).setDuration(2000);

        ObjectAnimator objectAnimatorTv2Y = ObjectAnimator.ofFloat(tv2, "translationY", -Utils.dp2px(50)).setDuration(2000);

        AnimatorSet animatorSet1 = new AnimatorSet();
        animatorSet1.playTogether(objectAnimatorTv1Y, objectAnimatorTv1Size, objectAnimatorTv1Color,objectAnimatorTv2Y);
        animatorSet1.start();


        // 第二阶段
        ObjectAnimator objectAnimatorTv1Y2 = ObjectAnimator.ofFloat(tv1, "translationY", -Utils.dp2px(50),-Utils.dp2px(100)).setDuration(2000);
        ObjectAnimator objectAnimatorTv1Size2 = ObjectAnimator.ofFloat(tv1, "haSize", 5,0).setDuration(2000);
        ObjectAnimator objectAnimatorTv1Color2= ObjectAnimator.ofInt(tv1, "haColor", 5,0).setDuration(2000);

        ObjectAnimator objectAnimatorTv2Y2 = ObjectAnimator.ofFloat(tv2, "translationY", -Utils.dp2px(50),-Utils.dp2px(100)).setDuration(2000);
        ObjectAnimator objectAnimatorTv2Size2 = ObjectAnimator.ofFloat(tv2, "haSize", 5).setDuration(2000);
        ObjectAnimator objectAnimatorTv2Color2 = ObjectAnimator.ofInt(tv2, "haColor", 5).setDuration(2000);

        final AnimatorSet animatorSet2 = new AnimatorSet();


        animatorSet2.playTogether(objectAnimatorTv1Y2, objectAnimatorTv1Size2, objectAnimatorTv1Color2,objectAnimatorTv2Y2,objectAnimatorTv2Size2,objectAnimatorTv2Color2);

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










    }
}

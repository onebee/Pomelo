package one.one.com.a08_animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author diaokaibin@gmail.com on 2019-07-17.
 */
public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

//
//        ImageView imageView = findViewById(R.id.iv);
//        imageView.animate().translationX(200).setDuration(2000).setStartDelay(2000).start();


        BreathLightView view = findViewById(R.id.view);

//        ObjectAnimator animator = ObjectAnimator.ofFloat(circleView, "RADIUS", Utils.dp2px(200));
//        animator.setDuration(2000);
//        animator.setStartDelay(1000);
//        animator.setAutoCancel(false);
//        animator.start();
//
//        animator.setRepeatMode(INFINITE);
//
//
//        CountDownTimer countDownTimer = new CountDownTimer(5000,1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//
//            }
//
//            @Override
//            public void onFinish() {
//
//                ObjectAnimator animator = ObjectAnimator.ofFloat(circleView, "RADIUS", Utils.dp2px(20));
//                animator.setDuration(2000);
//                animator.setStartDelay(1000);
//                animator.start();
//            }
//        };
//
//        countDownTimer.start();

//        Point targetPoint = new Point(500,500);
//
//        ObjectAnimator animator = ObjectAnimator.ofObject(view, "point", new PointEvaluator(), targetPoint);
//        animator.setStartDelay(2000);
//        animator.setDuration(2000);
//        animator.start();


//        PointF targetPoint = new PointF(500,500);
//        ObjectAnimator.ofInt(view,)

//        ObjectAnimator pointAnimator = ObjectAnimator.ofObject(view, "mPoint", new PointFEvaluator(), new PointF(0, 0),
//                new PointF(500, 500));
//
//        ObjectAnimator animator1 = ObjectAnimator.ofObject(view, "color", new ArgbEvaluator(), 0x33669900);
//        animator1.setStartDelay(2000);
//        animator1.setDuration(2000);
//
//        AnimatorSet animatorSet = new AnimatorSet();
//
//        animatorSet.playTogether(pointAnimator,animator1);


//        ObjectAnimator.ofInt

//        animator.setEvaluator();
//        animatorSet.start();


        final ObjectAnimator radius = ObjectAnimator.ofFloat(view, "radius", Utils.dp2px(55), Utils.dp2px(5));
        final ObjectAnimator color = ObjectAnimator.ofObject(view, "color", new ArgbEvaluator(), 0xffadd083);

        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(5000);

        animatorSet.playTogether(radius, color);

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animatorSet.playTogether(radius, color);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        animatorSet.start();




    }

    class PointEvaluator implements TypeEvaluator<Point> {

        @Override
        public Point evaluate(float fraction, Point startValue, Point endValue) {
            float x = startValue.x + (endValue.x - startValue.x) * fraction;
            float y = startValue.y + (endValue.y - startValue.y) * fraction;
            return new Point((int) x, (int) y);
        }
    }
}

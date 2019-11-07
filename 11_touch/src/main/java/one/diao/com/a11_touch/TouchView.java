package one.diao.com.a11_touch;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author diaokaibin@gmail.com on 2019/1/28.
 */
public class TouchView extends View {
    public TouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

//        event.getActionIndex() 触摸的是第几根手指
        if (event.getActionMasked() == MotionEvent.ACTION_UP) {
            performClick();
        }

//        MotionEvent.ACTION_UP;
//        MotionEvent.ACTION_POINTER_DOWN;
//        MotionEvent.ACTION_POINTER_UP;

        return true;
    }



}

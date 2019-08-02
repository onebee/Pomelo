package one.diao.com.a09_bitmap_drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * @author diaokaibin@gmail.com on 2019-07-29.
 */
public class PView extends ProgressBar {



    public PView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}

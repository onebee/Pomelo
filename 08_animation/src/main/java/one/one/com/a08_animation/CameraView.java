package one.one.com.a08_animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author diaokaibin@gmail.com on 2018/12/25.
 */
public class CameraView extends View {
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    {


    }
    public CameraView(Context context,@Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < 50; i++) {
            canvas.drawLine(0, 100 * (i + 1), getWidth(), 100 * (i + 1), mPaint);
            canvas.drawLine(100 * (i + 1), 0, 100 * (i + 1), getHeight(), mPaint);

        }

        canvas.drawBitmap(Utils.getAvatar(getResources(),400),0,0,mPaint);
    }
}

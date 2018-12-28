package one.one.com.a08_animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @author diaokaibin@gmail.com on 2018/12/25.
 */
public class CircleView extends View {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private float RADIUS = Utils.dp2px(25);

    {
        mPaint.setColor(Color.RED);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(getWidth()/2,getHeight()/2,RADIUS,mPaint);
        Log.d("-----", "  RADIUS :" + RADIUS);

    }

    public void setRADIUS(float RADIUS) {
        this.RADIUS = RADIUS;
        invalidate();
    }

    public float getRADIUS() {
        return RADIUS;
    }
}

package one.one.com.a08_animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author diaokaibin@gmail.com on 2019-07-22.
 */
public class BreathLightView extends View {


    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    float radius = Utils.dp2px(2);
    int color = 0xffff0000;

    {

        mPaint.setColor(color);
        mPaint.setStrokeWidth(Utils.dp2px(25));


    }

    public BreathLightView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(color);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, mPaint);

    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
        invalidate();
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        invalidate();
    }
}

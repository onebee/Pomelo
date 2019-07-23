package one.one.com.a08_animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author diaokaibin@gmail.com on 2019-07-22.
 */
public class PonitView extends View {
    Paint paint = new Paint();

    Point point = new Point(0, 0);



    int color = 0xffff0000;


    {

        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(Utils.dp2px(35));
    }

    public PonitView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(color);
        canvas.drawPoint(point.x, point.y, paint);

    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
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

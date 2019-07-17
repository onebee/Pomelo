package com.cheeath.pomelo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.cheeath.pomelo.Utils;

public class Dashboard extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();
    private static float RADIUS = Utils.dp2px(150);
    private static int ANGLE = 120;
    private static final float LENGTH = Utils.dp2px(100);


    private int roate = 180;

    private PathEffect effect;
    Path dash = new Path();

    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(Utils.dp2px(2));
        dash.addRect(0, 0, Utils.dp2px(2), Utils.dp2px(10), Path.Direction.CCW);
        Path arc = new Path();
        arc.addArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS, 90 + ANGLE / 2, 360 - ANGLE);

        PathMeasure pathMeasure = new PathMeasure(arc, false);
        effect = new PathDashPathEffect(dash, (pathMeasure.getLength() - Utils.dp2px(2)) / 20, 0, PathDashPathEffect.Style.ROTATE);
    }

    public Dashboard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawArc(getWidth() / 2 - RADIUS,
                getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS,
                getHeight() / 2 + RADIUS,
                ANGLE / 2 + 90,
                360 - ANGLE,
                false, paint);

        // 画刻度
        paint.setPathEffect(effect);
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS, ANGLE / 2 + 90, 360 - ANGLE, false, paint);
        paint.setPathEffect(null);



        //画指针 TODO : 坐标的计算
        canvas.drawLine(getWidth() / 2, getHeight() / 2,
                (float) Math.cos(Math.toRadians(roate)) * LENGTH + getWidth() / 2,
                (float) Math.sin(Math.toRadians(roate)) * LENGTH + getHeight() / 2, paint
        );




    }

    public void setRoate(int roate) {
        this.roate = roate;
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        path.reset();
    }
}

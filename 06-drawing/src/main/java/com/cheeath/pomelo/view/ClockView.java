package com.cheeath.pomelo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.cheeath.pomelo.Utils;

/**
 * @author diaokaibin@gmail.com on 2018/11/7.
 */
public class ClockView extends View {

    Paint secondPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint hourPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint minutePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    Path path = new Path();
    private static float RADIUS = Utils.dp2px(150);
    private static int ANGLE = 120;
    private static final float LENGTH = Utils.dp2px(100);
    private static final float miunteLENGTH = Utils.dp2px(80);
    private static final float hourLENGTH = Utils.dp2px(50);

    private int secondHand = 0;
    private int minuteHand = 0;
    private int hourHand = -90;

    private PathEffect effect;
    Path dash = new Path();


    {
        secondPaint.setColor(Color.parseColor("#B84E2A"));
        hourPaint.setColor(Color.parseColor("#239FFB"));
        minutePaint.setColor(Color.parseColor("#000000"));

        secondPaint.setStyle(Paint.Style.STROKE);
        hourPaint.setStyle(Paint.Style.STROKE);
        minutePaint.setStyle(Paint.Style.STROKE);

        secondPaint.setStrokeCap(Paint.Cap.ROUND);
        hourPaint.setStrokeCap(Paint.Cap.ROUND);
        minutePaint.setStrokeCap(Paint.Cap.ROUND);

        secondPaint.setStrokeWidth(Utils.dp2px(2));
        minutePaint.setStrokeWidth(Utils.dp2px(4));
        hourPaint.setStrokeWidth(Utils.dp2px(6));

        dash.addRect(0, 0, Utils.dp2px(2), Utils.dp2px(10), Path.Direction.CCW);
        Path arc = new Path();
        arc.addArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS, 0, 360);

        PathMeasure pathMeasure = new PathMeasure(arc, false);
        effect = new PathDashPathEffect(dash, (pathMeasure.getLength() - Utils.dp2px(2)) / 60, 0, PathDashPathEffect.Style.ROTATE);


    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS, 0, 360, false, secondPaint);

        // 画刻度
        secondPaint.setPathEffect(effect);
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS, 0, 360, false, secondPaint);
        secondPaint.setPathEffect(null);

        // 画秒针的刻度
        canvas.drawLine(getWidth() / 2, getHeight() / 2,
                (float) Math.cos(Math.toRadians(secondHand)) * LENGTH + getWidth() / 2,
                (float) Math.sin(Math.toRadians(secondHand)) * LENGTH + getHeight() / 2, secondPaint
        );


        canvas.drawLine(getWidth() / 2, getHeight() / 2,
                (float) Math.cos(Math.toRadians(minuteHand)) * miunteLENGTH + getWidth() / 2,
                (float) Math.sin(Math.toRadians(minuteHand)) * miunteLENGTH + getHeight() / 2, minutePaint
        );

        canvas.drawLine(getWidth() / 2, getHeight() / 2,
                (float) Math.cos(Math.toRadians(hourHand)) * hourLENGTH + getWidth() / 2,
                (float) Math.sin(Math.toRadians(hourHand)) * hourLENGTH + getHeight() / 2, hourPaint
        );

    }

    public void setSecondHand(int secondHand) {
        this.secondHand = secondHand;
        if (secondHand == 360) {
            minuteHand = minuteHand + 6;
            secondHand = 0;
            if (minuteHand == 360) {
                minuteHand = 0;
                hourHand = hourHand + 6;
                if (hourHand == 360) {
                    hourHand = 0;
                }

            }
        }
        invalidate();
    }
}

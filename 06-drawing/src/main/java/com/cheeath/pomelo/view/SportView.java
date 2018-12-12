package com.cheeath.pomelo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.cheeath.pomelo.Utils;

public class SportView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint paintTv = new Paint(Paint.ANTI_ALIAS_FLAG);

    private String text = "aaa";

    private float RADIUS = Utils.dp2px(200);

    private float STROKE_WIDTH = Utils.dp2px(25);

    private float TEXT_SIZE = Utils.dp2px(70);
    Rect bounds = new Rect();

    Paint.FontMetrics mFontMetrics = new Paint.FontMetrics();

    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(STROKE_WIDTH);
        paint.setColor(Color.GRAY);
        paint.setStrokeCap(Paint.Cap.ROUND);


        paintTv.setStrokeWidth(55);
        paintTv.setTextSize(TEXT_SIZE);
        paintTv.setTextAlign(Paint.Align.CENTER);
        paintTv.setColor(Color.RED);
        paintTv.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"Quicksand-Regular.ttf"));
    }

    public SportView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.GRAY);
        canvas.drawArc(getWidth() / 2 - RADIUS,
                getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS,
                getHeight() / 2 + RADIUS,
                0, 360,
                false, paint);


        paint.setColor(Color.RED);
        canvas.drawArc(getWidth() / 2 - RADIUS,
                getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS,
                getHeight() / 2 + RADIUS,
                -60, 150,
                false, paint);

//方式1
//        paintTv.getTextBounds(text,0,text.length(),bounds);
        int offset1 = (bounds.top + bounds.bottom)/2;
//        canvas.drawText(text,getWidth()/2,getHeight()/2-offset,paintTv);

        paintTv.setTextAlign(Paint.Align.CENTER);
        paintTv.getFontMetrics(mFontMetrics);

        float offset = (mFontMetrics.ascent + mFontMetrics.descent)/2;
        canvas.drawText(text,getWidth()/2,getHeight()/2-offset,paintTv);

        // 文字的左对齐
        paintTv.setTextSize(Utils.dp2px(100));
        paintTv.setTextAlign(Paint.Align.LEFT);
        paintTv.getTextBounds(text,0,text.length(),bounds);
        canvas.drawText(text,0-bounds.left,555,paintTv);

        paintTv.setTextSize(Utils.dp2px(15));
        canvas.drawText(text,0,555 + paintTv.getFontSpacing(),paintTv);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);


    }
}

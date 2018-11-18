package com.cheeath.pomelo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.StaticLayout;
import android.util.AttributeSet;
import android.view.View;

public class SportView extends View {

    Paint paint = new Paint();

    {


    }

    public SportView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Rect bounds = new Rect();
        paint.getTextBounds("北大", 0, "北大".length(), bounds);

//        canvas.drawText("aaa",0,);

        StaticLayout staticLayout;

    }
}

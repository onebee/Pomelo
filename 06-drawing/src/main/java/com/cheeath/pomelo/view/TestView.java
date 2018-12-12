package com.cheeath.pomelo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class TestView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();
    PathMeasure pathMeasure ;

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        path.setFillType(Path.FillType.WINDING);
        canvas.drawPath(path, paint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // onSizeChanged 避免过多的调用
        path.reset();
        path.addRect(getWidth() / 2 - 150, getHeight() / 2 - 300, getWidth() / 2 + 150,
                getHeight() / 2, Path.Direction.CCW
        );

        path.addCircle(getWidth() / 2, getHeight() / 2, 150, Path.Direction.CCW);
       pathMeasure =  new PathMeasure(path,false);
        float length = pathMeasure.getLength();
        Log.e("TestView" ,"length  " +  length);
//        pathMeasure.getPosTan()
    }
}

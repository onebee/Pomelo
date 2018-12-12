package one.diao.com.android.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import one.diao.com.android.Utils;

/**
 * @author diaokaibin@gmail.com on 2018/12/12.
 */
public class CustomView extends View {


    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint.FontMetrics mFontMetrics = new Paint.FontMetrics();


    {
        mPaint.setColor(Color.BLUE);
        mPaint.setTextSize(Utils.dp2px(25));
        mPaint.getFontMetrics(mFontMetrics);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {


        super(context, attrs);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.clipRect(100, 100, 400, 400);
//         方式1
//        canvas.drawBitmap(Utils.getAvatar(getResources(),400),200,100,mPaint);

//        // 方式2
//        canvas.translate(200,100);
//        canvas.drawBitmap(Utils.getAvatar(getResources(),400),0,0,mPaint);

//        canvas.rotate(45,200,200);
//        canvas.drawBitmap(Utils.getAvatar(getResources(),400),0,0,mPaint);

        canvas.drawText("这是坐标的(0,0)点",0,-mFontMetrics.top,mPaint);

        for (int i = 0; i < 50; i++) {
            canvas.drawLine(0, 100 * (i + 1), getWidth(), 100 * (i + 1), mPaint);
            canvas.drawLine(100 * (i + 1), 0, 100 * (i + 1), getHeight(), mPaint);

        }

        canvas.translate(200, 200);
        canvas.rotate(45,  200, 200);
        canvas.drawBitmap(Utils.getAvatar(getResources(), 400), 0, 0, mPaint);
    }


}

package one.diao.com.android.view;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import one.diao.com.android.Utils;

/**
 * @author diaokaibin@gmail.com on 2018/12/9.
 */
public class CameraView extends View {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint.FontMetrics mFontMetrics = new Paint.FontMetrics();
    float SPACE = Utils.dp2px(50);
    Camera mCamera = new Camera();


    {
        mPaint.setColor(Color.BLUE);
        mPaint.setTextSize(Utils.dp2px(25));
        mPaint.getFontMetrics(mFontMetrics);
        mCamera.rotateX(30);
        mCamera.setLocation(0,0,Utils.getZForCamera(-8));
//        mCamera.rotateY(-45);
    }

    public CameraView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < 50; i++) {
            canvas.drawLine(0, SPACE * (i + 1), getWidth(), SPACE * (i + 1), mPaint);
            canvas.drawLine(SPACE * (i + 1), 0, SPACE * (i + 1), getHeight(), mPaint);

        }
        // 倒着想

        // 绘制上部分
        canvas.save();
        canvas.translate(100 + 400 / 2, 100 + 400 / 2);
        canvas.rotate(-30);
        canvas.clipRect(-400,-400,400,0);
        canvas.rotate(30);
        canvas.translate(-(100 + 400 / 2), -(100 + 400 / 2));
        canvas.drawBitmap(Utils.getAvatar(getResources(), 400), 100, 100, mPaint);
        canvas.restore();

        // 绘制下部分
        canvas.save();
        canvas.translate(100 + 400 / 2, 100 + 400 / 2);
        canvas.rotate(-30);
        mCamera.applyToCanvas(canvas);
        canvas.clipRect(-400,0,400,400);
        canvas.rotate(30);
        canvas.translate(-(100 + 400 / 2), -(100 + 400 / 2));
        canvas.drawBitmap(Utils.getAvatar(getResources(), 400), 100, 100, mPaint);
        canvas.restore();

//        canvas.rotate(45,400/2,400/2);
//        mCamera.applyToCanvas(canvas);
//        canvas.drawBitmap(Utils.getAvatar(getResources(),400),100,100,mPaint);



    }
}

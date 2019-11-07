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
public class AvaterView extends View {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint.FontMetrics mFontMetrics = new Paint.FontMetrics();
    float SPACE = Utils.dp2px(50);
    Camera mCamera = new Camera();

    float IMAGE_WIDTH = Utils.dp2px(100);


    {
        mPaint.setColor(Color.BLUE);
        mPaint.setTextSize(Utils.dp2px(25));
        mPaint.getFontMetrics(mFontMetrics);
        mCamera.rotateX(30);
        mCamera.setLocation(0, 0, Utils.getZForCamera(-8));
        mCamera.rotateX(10);
//        mCamera.setLocation(0,0,Utils.getZForCamera(-8));
    }

    public AvaterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < 50; i++) {
            canvas.drawLine(0, SPACE * (i + 1), getWidth(), SPACE * (i + 1), mPaint);
            canvas.drawLine(SPACE * (i + 1), 0, SPACE * (i + 1), getHeight(), mPaint);

        }

//        canvas.clipRect(SPACE, SPACE, Utils.dp2px(100), Utils.dp2px(100));
//        canvas.drawBitmap(Utils.getAvatar(getResources(), (int) Utils.dp2px(100)),Utils.dp2px(200),Utils.dp2px(100),mPaint);

//        canvas.translate(Utils.dp2px(200), Utils.dp2px(100));
//        canvas.drawBitmap(Utils.getAvatar(getResources(), (int) Utils.dp2px(100)), 0, 0, mPaint);


//        canvas.rotate(45,IMAGE_WIDTH/2,IMAGE_WIDTH/2);
//        canvas.drawBitmap(Utils.getAvatar(getResources(),(int) IMAGE_WIDTH),0,0,mPaint);

//        canvas.translate(Utils.dp2px(200),Utils.dp2px(100));
//        canvas.rotate(45);
//        canvas.drawBitmap(Utils.getAvatar(getResources(),(int) IMAGE_WIDTH),0,0,mPaint);


        // 绘制上部分
        canvas.save();
        canvas.translate((Utils.dp2px(100) + Utils.dp2px(50)), (Utils.dp2px(100) + Utils.dp2px(50)));
        canvas.rotate(-25);
        canvas.clipRect(-Utils.dp2px(100), -Utils.dp2px(100), Utils.dp2px(100), 0);
        canvas.rotate(25);
        canvas.translate(-(Utils.dp2px(100) + Utils.dp2px(50)), -(Utils.dp2px(100) + Utils.dp2px(50)));
        canvas.drawBitmap(Utils.getAvatar(getResources(), (int) Utils.dp2px(100)), Utils.dp2px(100), Utils.dp2px(100), mPaint);
        canvas.restore();


        // 绘制下部分
        canvas.translate((Utils.dp2px(100) + Utils.dp2px(50)), (Utils.dp2px(100) + Utils.dp2px(50)));
        // 将复杂的变化 一次应用到canvas 上面
        canvas.rotate(-25);
        mCamera.applyToCanvas(canvas);
        canvas.clipRect(-Utils.dp2px(100), 0, Utils.dp2px(100), Utils.dp2px(100));
        canvas.rotate(25);
        canvas.translate(-(Utils.dp2px(100) + Utils.dp2px(50)), -(Utils.dp2px(100) + Utils.dp2px(50)));
        canvas.drawBitmap(Utils.getAvatar(getResources(), (int) Utils.dp2px(100)), Utils.dp2px(100), Utils.dp2px(100), mPaint);

    }
}

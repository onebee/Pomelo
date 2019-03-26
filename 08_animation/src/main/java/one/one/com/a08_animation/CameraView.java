package one.one.com.a08_animation;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author diaokaibin@gmail.com on 2018/12/9.
 */
public class CameraView extends View {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint.FontMetrics mFontMetrics = new Paint.FontMetrics();

    Camera mCamera = new Camera();

    float topFlip = 0;
    float bottomFlip = 0;
    float flipRotation = 0;

    public float getTopFlip() {
        return topFlip;
    }

    public void setTopFlip(float topFlip) {
        this.topFlip = topFlip;
        invalidate();
    }

    public float getBottomFlip() {
        return bottomFlip;
    }

    public void setBottomFlip(float bottomFlip) {
        this.bottomFlip = bottomFlip;
        invalidate();
    }

    public float getFlipRotation() {
        return flipRotation;
    }

    public void setFlipRotation(float flipRotation) {
        this.flipRotation = flipRotation;
        invalidate();
    }

    {
        mPaint.setColor(Color.BLUE);
        mPaint.setTextSize(Utils.dp2px(25));
        mPaint.getFontMetrics(mFontMetrics);

        mCamera.setLocation(0, 0, Utils.getZForCamera(-8));
//        mCamera.rotateY(-45);
    }

    public CameraView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < 50; i++) {
            canvas.drawLine(0, 100 * (i + 1), getWidth(), 100 * (i + 1), mPaint);
            canvas.drawLine(100 * (i + 1), 0, 100 * (i + 1), getHeight(), mPaint);

        }
        // 倒着想

        // 绘制上部分
        canvas.save();
        canvas.translate(100 + 400 / 2, 100 + 400 / 2);
        canvas.rotate(-flipRotation);
        mCamera.save();
        mCamera.rotateX(topFlip);
        mCamera.applyToCanvas(canvas);
        mCamera.restore();
        canvas.clipRect(-400, -400, 400, 0);
        canvas.rotate(flipRotation);
        canvas.translate(-(100 + 400 / 2), -(100 + 400 / 2));
        canvas.drawBitmap(Utils.getAvatar(getResources(), 400), 100, 100, mPaint);
        canvas.restore();

        // 绘制下部分
        canvas.save();
        canvas.translate(100 + 400 / 2, 100 + 400 / 2);
        canvas.rotate(-flipRotation);
        mCamera.save();
        mCamera.rotateX(bottomFlip);
        mCamera.applyToCanvas(canvas);
        mCamera.restore();
        canvas.clipRect(-400, 0, 400, 400);
        canvas.rotate(flipRotation);
        canvas.translate(-(100 + 400 / 2), -(100 + 400 / 2));
        canvas.drawBitmap(Utils.getAvatar(getResources(), 400), 100, 100, mPaint);
        canvas.restore();

//        canvas.rotate(45,400/2,400/2);
//        mCamera.applyToCanvas(canvas);
//        canvas.drawBitmap(Utils.getAvatar(getResources(),400),100,100,mPaintLeft);


    }
}

package one.diao.com.a12_scalable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author diaokaibin@gmail.com on 2019-08-14.
 */
public class ScaleImageView2 extends View {


    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    float IMAGE_WIDTH = Utils.dp2px(200);
    private Bitmap bitmap;

    float originOffsetX;
    float originOffsetY;
    float smallScale;
    float bigScale;


    public ScaleImageView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        bitmap = Utils.getAvatar(getResources(), (int) IMAGE_WIDTH);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        originOffsetX = (getWidth()-bitmap.getWidth())/2.0f;
        originOffsetY = (getWidth()-bitmap.getWidth())/2.0f;
        if ((float) bitmap.getWidth() / bitmap.getHeight() > (float) getWidth() / getHeight()) {

            smallScale = (float) getWidth() / bitmap.getWidth();
            bigScale = (float) getHeight() / bitmap.getHeight();
        } else {
            smallScale = (float) getHeight() / bitmap.getHeight();
            bigScale = (float) getWidth() / bitmap.getWidth();
        }

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float scale = bigScale;
        canvas.scale(scale,scale,getWidth()/2f,getHeight()/2f);
        canvas.drawBitmap(bitmap, originOffsetX, originOffsetY, mPaint);

    }

}

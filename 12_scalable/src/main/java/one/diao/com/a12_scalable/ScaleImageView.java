package one.diao.com.a12_scalable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * @author diaokaibin@gmail.com on 2019/2/2.
 */
public class ScaleImageView extends View {

    Bitmap mBitmap;
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


    float WIDTH = Utils.dp2px(300);
    float offsetX,offsetY;

    {



    }
    public ScaleImageView(Context context,  @Nullable AttributeSet attrs) {
        super(context, attrs);

        mBitmap = Utils.getAvatar(getResources(),(int) WIDTH);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        offsetX = (getWidth()-WIDTH)/2f;
        offsetY = (getHeight()-WIDTH)/2f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBitmap,offsetX,offsetY,mPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

}

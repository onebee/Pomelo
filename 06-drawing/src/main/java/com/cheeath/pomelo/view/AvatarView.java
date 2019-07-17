package com.cheeath.pomelo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.cheeath.pomelo.R;
import com.cheeath.pomelo.Utils;

public class AvatarView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private static final float WIDTH = Utils.dp2px(300);
    private static final float PADDING = Utils.dp2px(50);
    private static final float EDGE_WIDTH = Utils.dp2px(10);
    Bitmap bitmap;
    Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

    RectF saveRectf = new RectF();

    {
        bitmap = getAvatar((int) WIDTH);

    }

    public AvatarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.BLUE);
        canvas.drawOval(PADDING,
                PADDING,
                PADDING + WIDTH,
                PADDING + WIDTH,
                paint);

        paint.setColor(Color.RED);


//        int saveLayer = canvas.saveLayer(saveRectf, paint);
        canvas.drawOval(PADDING + EDGE_WIDTH,
                PADDING + EDGE_WIDTH,
                PADDING + WIDTH - EDGE_WIDTH,
                PADDING + WIDTH - EDGE_WIDTH,
                paint);
        paint.setXfermode(xfermode);


//        canvas.drawBitmap(bitmap, PADDING, PADDING, paint);
        paint.setXfermode(null);

//        canvas.restoreToCount(saveLayer);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        saveRectf.set(PADDING, PADDING, PADDING + WIDTH, PADDING + WIDTH);
    }

    Bitmap getAvatar(int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // 费时的方法 这样设置 只取图片的宽高
        BitmapFactory.decodeResource(getResources(), R.drawable.avater, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(getResources(), R.drawable.avater, options);
    }
}

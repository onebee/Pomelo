package com.cheeath.pomelo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.annotation.Nullable;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.cheeath.pomelo.R;
import com.cheeath.pomelo.Utils;

public class ImageTextView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint paintTv = new Paint(Paint.ANTI_ALIAS_FLAG);

    private String text = "aaa";

    private float RADIUS = Utils.dp2px(200);

    private float STROKE_WIDTH = Utils.dp2px(5);

    private float TEXT_SIZE = Utils.dp2px(70);

    public static final float IMAGE_WIDTH = Utils.dp2px(200);
    public static final float IMAGE_OFFSET = Utils.dp2px(80);


    Rect bounds = new Rect();
    Paint.FontMetrics mFontMetrics = new Paint.FontMetrics();

    TextPaint mTextPaint = new TextPaint();
    StaticLayout staticLayout;

    float[] cutWidth = new float[1];

    String str = "Chrome 把浏览器不同程序的功能看做服务，" +
            "这些服务可以方便的分割为不同的进程或者合并为一个进程。" +
            "以 Broswer Process 为例，如果 Chrome 运行在强大的硬件" +
            "上，它会分割不同的服务到不同的进程，这样 Chrome 整体的运行会更加稳定，" +
            "但是如果 Chrome 运行在资源贫瘠的设备上" +
            "，这些服务又会合并到同一个进程中运行，这样可以节省内存，示意图如下。iframe 的渲染 -- Site Isolation" +
            "在上面的进程图中我们还可以看到一些进程下还存在着 Subframe，这就是 Site Isolation 机制作用的结果。" +
            "Site Isolation 机制从 Chrome 67 开始默认启用。这种机制允许在同一个 Tab 下的跨站 iframe 使用单独的进程来渲染，这样会更为安全。";


    Bitmap mBitmap;


    {
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(STROKE_WIDTH);
        paint.setColor(Color.BLUE);
        paint.setTextSize(Utils.dp2px(25));
        paint.setStrokeCap(Paint.Cap.ROUND);

        mTextPaint.setTextSize(Utils.dp2px(15));

        paint.getFontMetrics(mFontMetrics);

        // 在外面new 的时候 文字会是竖直的 TODO : 疑问
//        staticLayout = new StaticLayout("我们在做服务开发的时候，经常需要思考这么几个问题：如何" +
//                "拆分服务，目前已经提供了哪些服务，还有哪些可以完善的服务。脑图工具可以很好的帮助我解决这些问题" +
//                "，脑图节点就代表着如何拆分，已经提供服务的用已完成标记，认为有问题可以改善的服务可以用感叹" +
//                "号标记，还未做的服务用未启动标记。脑图工具有很多可选，像业内知名的有 xmind, mindmana" +
//                "ger 。随着云概念的火热，现在也有很多的在线脑图工具可选择，包括我们的鱼骨软件也都有提供在线脑图这样的功能。",
//                mTextPaint,
//                getWidth(),
//                Layout.Alignment.ALIGN_NORMAL,
//                1,
//                0,
//                false
//        );

//        Log.d(" st","st -- "  + staticLayout);

        mBitmap = getAvatar((int) Utils.dp2px(200));

    }

    public ImageTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        StaticLayout  绘制多行文字 new 对象不要在onDraw 里
//        Log.d(" st","st"  + staticLayout);
//        staticLayout.draw(canvas);

        float verticalOffset = -mFontMetrics.top;

        canvas.drawBitmap(mBitmap, getWidth() - IMAGE_WIDTH, IMAGE_OFFSET, paint);
        canvas.drawText(str, 0, str.length(), 0, verticalOffset, paint);
        int length = str.length();
        for (int start = 0; start < length; ) {
            int maxWidth;
            float textTop = verticalOffset + mFontMetrics.top;
            float textBottom = verticalOffset + mFontMetrics.bottom;
            if (textTop > IMAGE_OFFSET && textTop < IMAGE_OFFSET + IMAGE_WIDTH ||
                    textBottom > IMAGE_OFFSET && textBottom < IMAGE_OFFSET + IMAGE_WIDTH
                    ) {

                // 文字和图片在同一行
                maxWidth = (int) (getWidth() - IMAGE_WIDTH);
            } else {
                maxWidth = getWidth();
            }

            int count = paint.breakText(str, start, length, true, maxWidth, cutWidth);
            canvas.drawText(str,start,start+count,0,verticalOffset,paint);
            start += count;
            verticalOffset += paint.getFontSpacing();

        }


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);


    }

    Bitmap getAvatar(int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.avater, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(getResources(), R.drawable.avater, options);
    }
}

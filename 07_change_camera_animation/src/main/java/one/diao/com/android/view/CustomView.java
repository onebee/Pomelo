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

    {
        mPaint.setColor(Color.BLUE);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {


        super(context, attrs);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.clipRect(0, 0, 200, 200);

        canvas.drawBitmap(Utils.getAvatar(getResources(),400),0,0,mPaint);
    }


}

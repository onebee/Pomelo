package one.one.com.a08_animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * @author diaokaibin@gmail.com on 2019/1/24.
 */
public class HaView extends android.support.v7.widget.AppCompatTextView {


    private float TEXT_SIZE_SMALL = Utils.dp2px(25);
    private float TEXT_SIZE_BIG = Utils.dp2px(35);

    private float MARGIN = Utils.dp2px(10);

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint.FontMetrics mFontMetrics = new Paint.FontMetrics();

    float haSize = 0;
    int haColor = 0;


    int[] colors = {0xFF9091A2,
            0xFFA9AAB8,
            0xFFBEBFC9,
            0xFFDBDBE2,
            0xFFFFFFFF,
            0xFFFFFFFF,
            0xFFFFFFFF,

    };


    public int getHaColor() {
        return haColor;
    }

    public void setHaColor(int haColor) {
        this.haColor = haColor;
        invalidate();
    }

    public float getHaSize() {
        return haSize;
    }

    public void setHaSize(float haSize) {
        this.haSize = haSize;
        invalidate();
    }

    {
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(TEXT_SIZE_BIG);
        mPaint.setColor(Color.GRAY);
        mPaint.getFontMetrics(mFontMetrics);

    }

    public HaView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        float verticalOffset = -mFontMetrics.top;
        mPaint.setTextSize(TEXT_SIZE_SMALL + haSize);
        mPaint.setColor(colors[haColor]);
        canvas.drawText(getText().toString(), getWidth() / 2, getHeight() / 2, mPaint);


    }
}

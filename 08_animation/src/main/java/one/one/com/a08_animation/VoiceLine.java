package one.one.com.a08_animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author diaokaibin@gmail.com on 2018/12/27.
 */
public class VoiceLine extends View {

    Paint mPaintLeft = new Paint();
    Paint mPaintRight = new Paint();
    private float lengthX = Utils.dp2px(0);
    Shader shaderLinearLeft, shaderLinearRight;

    {
        mPaintLeft.setColor(Color.parseColor("#05FEFE"));
        mPaintLeft.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintLeft.setStrokeCap(Paint.Cap.ROUND);
        mPaintLeft.setStrokeWidth(50);


        mPaintRight.setColor(Color.parseColor("#05FEFE"));
        mPaintRight.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintRight.setStrokeCap(Paint.Cap.ROUND);
        mPaintRight.setStrokeWidth(50);


//        PathEffect pathEffect = new DashPathEffect(new float[]{10, 5}, 10);
//        mPaintLeft.setPathEffect(pathEffect);
//        mPaintLeft.setShadowLayer(Utils.dp2px(3), 0, 0, Color.RED);

//        mPaintLeft.setShader(shader);
//        mPaintLeft.setMaskFilter(new BlurMaskFilter(Utils.dp2px(6), BlurMaskFilter.Blur.SOLID));
//        mPaintLeft.setMaskFilter(new EmbossMaskFilter(new float[]{0, 1, 1}, 0.2f, 8, 10));


    }

    public VoiceLine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public float getLengthX() {
        return lengthX;
    }

    public void setLengthX(float length) {
        this.lengthX = length;
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (lengthX > 0) {
            shaderLinearLeft = getLeftGradient(0);
            mPaintLeft.setShader(shaderLinearLeft);

            shaderLinearRight = getLeftGradient(1);
            mPaintRight.setShader(shaderLinearRight);
        }


        canvas.drawLine(getWidth() / 2 - lengthX, getHeight() / 2, getWidth() / 2, getHeight() / 2, mPaintLeft);
        canvas.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2 + lengthX, getHeight() / 2, mPaintRight);


    }

    private LinearGradient getLeftGradient(int index) {
        if (index == 0) {
            return new LinearGradient(getWidth() / 2 - lengthX, getHeight() / 2, getWidth() / 2, getHeight() / 2, Color.parseColor("#056666"),
                    Color.parseColor("#09FEFE"), Shader.TileMode.CLAMP);
        } else {
            return new LinearGradient(getWidth() / 2, getHeight() / 2, getWidth() / 2 + lengthX, getHeight() / 2, Color.parseColor("#09FEFE"),
                    Color.parseColor("#056666"), Shader.TileMode.CLAMP);
        }
    }
}

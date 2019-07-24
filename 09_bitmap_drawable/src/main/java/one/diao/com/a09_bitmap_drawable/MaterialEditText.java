package one.diao.com.a09_bitmap_drawable;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;

/**
 * @author diaokaibin@gmail.com on 2019-07-23.
 */
public class MaterialEditText extends android.support.v7.widget.AppCompatEditText {

    private final static float TEXT_SIZE = Utils.dp2px(15);
    private final static float TEXT_MARGIN = Utils.dp2px(5);
    private static final int TEXT_HORIZONTAL_OFFSET = (int) Utils.dp2px(5);
    private static final int TEXT_VERTICAL_OFFSET = (int) Utils.dp2px(22);
    private static final int TEXT_ANIMATE_OFFSET = (int) Utils.dp2px(15);


    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private boolean useFloatingLabel;

    private Rect backgroundPadding = new Rect();
    private boolean floatingLabelShow;
    private ObjectAnimator animator;


    float floatingLabelFraction;

    {


    }


    public MaterialEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText);
        useFloatingLabel = typedArray.getBoolean(R.styleable.MaterialEditText_useFloatingLabel, true);
        typedArray.recycle();
        mPaint.setTextSize(TEXT_SIZE);
        onUseFloatingLabelChanged();


        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("-----", "  onTextChanged  ");
                if (useFloatingLabel) {

                    if (floatingLabelShow && TextUtils.isEmpty(s)) {
                        floatingLabelShow = false;
                        getAnimator().reverse();
                    } else if (!floatingLabelShow && !TextUtils.isEmpty(s)) {
                        floatingLabelShow = true;
                        getAnimator().start();
                    }


                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private ObjectAnimator getAnimator() {

        if (animator == null) {
            animator = ObjectAnimator.ofFloat(MaterialEditText.this, "floatingLabelFraction", 0, 1);
        }
        return animator;

    }

    private void onUseFloatingLabelChanged() {
        getBackground().getPadding(backgroundPadding);
        if (useFloatingLabel) {
            setPadding(getPaddingLeft(), (int) (backgroundPadding.top + TEXT_SIZE + TEXT_MARGIN), getPaddingRight(), getPaddingBottom());

        } else {
            setPadding(getPaddingLeft(), backgroundPadding.top, getPaddingRight(), getPaddingBottom());
        }

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.d("-----", " " + floatingLabelFraction);
        mPaint.setAlpha((int) ((0xff) * floatingLabelFraction));
        float extraOffset = TEXT_ANIMATE_OFFSET * (1 - floatingLabelFraction);

        canvas.drawText(getHint().toString(),   getPaddingLeft(), TEXT_VERTICAL_OFFSET + extraOffset, mPaint);

    }


    public float getFloatingLabelFraction() {
        return floatingLabelFraction;
    }

    public void setFloatingLabelFraction(float floatingLabelFraction) {
        this.floatingLabelFraction = floatingLabelFraction;
        invalidate();
    }

}


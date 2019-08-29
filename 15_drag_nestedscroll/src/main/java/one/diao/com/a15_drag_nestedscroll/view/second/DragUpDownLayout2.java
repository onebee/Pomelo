package one.diao.com.a15_drag_nestedscroll.view.second;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

import one.diao.com.a15_drag_nestedscroll.R;

/**
 * @author diaokaibin@gmail.com on 2019-08-29.
 */
public class DragUpDownLayout2 extends FrameLayout {

    View mView;
    ViewDragHelper mViewDragHelper;
    ViewConfiguration mConfiguration;
    ViewDragHelper.Callback mCallback;

    public DragUpDownLayout2(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mCallback = new DragListener();
        mViewDragHelper = ViewDragHelper.create(this, mCallback);
        mConfiguration = new ViewConfiguration();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mView = findViewById(R.id.view);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    class DragListener extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(@NonNull View view, int i) {
            return false;
        }
    }
}

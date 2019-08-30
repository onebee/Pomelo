package one.diao.com.a15_drag_nestedscroll.view.second;

import android.content.ClipData;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import one.diao.com.a15_drag_nestedscroll.R;

/**
 * @author diaokaibin@gmail.com on 2019-08-30.
 */
public class DragToCollectLayout2 extends RelativeLayout {

    ImageView avatarView;
    ImageView logoView;
    LinearLayout collectorLayout;

    CollectListener dragListener = new CollectListener();
    OnLongClickListener startStarter = new OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {

            ClipData clipData = ClipData.newPlainText("name", v.getContentDescription());
            return ViewCompat.startDragAndDrop(v, clipData, new DragShadowBuilder(v), null, 0);
        }
    };

    public DragToCollectLayout2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        avatarView = findViewById(R.id.avatarView);
        logoView = findViewById(R.id.logoView);
        collectorLayout = findViewById(R.id.collectorLayout);

        avatarView.setOnLongClickListener(startStarter);
        logoView.setOnLongClickListener(startStarter);

        collectorLayout.setOnDragListener(dragListener);

    }

    class CollectListener implements OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {

                case DragEvent.ACTION_DRAG_ENDED:

                    if (v instanceof LinearLayout) {
                        LinearLayout linearLayout = (LinearLayout)v;

                        TextView textView = new TextView(getContext());
                        textView.setText(event.getClipData().getItemAt(0).getText());
                        linearLayout.addView(textView);

                    }

                    break;
            }
            return true;
        }
    }
}

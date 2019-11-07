package one.diao.com.a14_view_pager;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private JellyTextView textView;

    public static int distance = 30;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = (JellyTextView) findViewById(R.id.tv);


    }

    public void click(View view) {

        switch (view.getId()) {
            case R.id.btn_scroll_to:
                textView.scrollTo(distance, 0);
                distance += 10;
                break;
            case R.id.btn_scroll_by:
                textView.scrollBy(30, 0);
                break;
            case R.id.btn_sping_back:
                //不知道为什么第一次调用会贴墙，即到达x=0的位置
                textView.spingBack();
                break;
        }

    }
}

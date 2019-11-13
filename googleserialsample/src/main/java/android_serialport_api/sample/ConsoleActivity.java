package android_serialport_api.sample;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import java.io.IOException;

public class ConsoleActivity extends SerialPortActivity {
    EditText mReception;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.console);
        this.mReception = (EditText) findViewById(R.id.EditTextReception);
        ((EditText) findViewById(R.id.EditTextEmission)).setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                CharSequence t = v.getText();
                char[] text = new char[t.length()];
                for (int i = 0; i < t.length(); i++) {
                    text[i] = t.charAt(i);
                }
                try {
                    ConsoleActivity.this.mOutputStream.write(new String(text).getBytes());
                    ConsoleActivity.this.mOutputStream.write(10);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onDataReceived(final byte[] buffer, final int size) {
        runOnUiThread(new Runnable() {
            public void run() {
                if (ConsoleActivity.this.mReception != null) {
                    ConsoleActivity.this.mReception.append(new String(buffer, 0, size));
                }
            }
        });
    }
}

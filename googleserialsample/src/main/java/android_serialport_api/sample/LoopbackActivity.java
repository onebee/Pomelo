package android_serialport_api.sample;

import android.os.Bundle;
import android.widget.TextView;
import java.io.IOException;

public class LoopbackActivity extends SerialPortActivity {
    boolean mByteReceivedBack;
    Object mByteReceivedBackSemaphore = new Object();
    Integer mCorrupted = new Integer(0);
    Integer mIncoming = new Integer(0);
    Integer mLost = new Integer(0);
    Integer mOutgoing = new Integer(0);
    SendingThread mSendingThread;
    TextView mTextViewCorrupted;
    TextView mTextViewIncoming;
    TextView mTextViewLost;
    TextView mTextViewOutgoing;
    byte mValueToSend;

    private class SendingThread extends Thread {
        private SendingThread() {
        }

        /* synthetic */ SendingThread(LoopbackActivity loopbackActivity, SendingThread sendingThread) {
            this();
        }

        public void run() {
            while (!isInterrupted()) {
                synchronized (LoopbackActivity.this.mByteReceivedBackSemaphore) {
                    LoopbackActivity.this.mByteReceivedBack = false;
                    try {
                        if (LoopbackActivity.this.mOutputStream != null) {
                            LoopbackActivity.this.mOutputStream.write(LoopbackActivity.this.mValueToSend);
                            LoopbackActivity loopbackActivity = LoopbackActivity.this;
                            loopbackActivity.mOutgoing = Integer.valueOf(loopbackActivity.mOutgoing.intValue() + 1);
                            try {
                                LoopbackActivity.this.mByteReceivedBackSemaphore.wait(100);
                                if (LoopbackActivity.this.mByteReceivedBack) {
                                    LoopbackActivity loopbackActivity2 = LoopbackActivity.this;
                                    loopbackActivity2.mIncoming = Integer.valueOf(loopbackActivity2.mIncoming.intValue() + 1);
                                } else {
                                    LoopbackActivity loopbackActivity3 = LoopbackActivity.this;
                                    loopbackActivity3.mLost = Integer.valueOf(loopbackActivity3.mLost.intValue() + 1);
                                }
                                LoopbackActivity.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        LoopbackActivity.this.mTextViewOutgoing.setText(LoopbackActivity.this.mOutgoing.toString());
                                        LoopbackActivity.this.mTextViewLost.setText(LoopbackActivity.this.mLost.toString());
                                        LoopbackActivity.this.mTextViewIncoming.setText(LoopbackActivity.this.mIncoming.toString());
                                        LoopbackActivity.this.mTextViewCorrupted.setText(LoopbackActivity.this.mCorrupted.toString());
                                    }
                                });
                            } catch (InterruptedException e) {
                            }
                        } else {
                            return;
                        }
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        return;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loopback);
        this.mTextViewOutgoing = (TextView) findViewById(R.id.TextViewOutgoingValue);
        this.mTextViewIncoming = (TextView) findViewById(R.id.TextViewIncomingValue);
        this.mTextViewLost = (TextView) findViewById(R.id.textViewLostValue);
        this.mTextViewCorrupted = (TextView) findViewById(R.id.textViewCorruptedValue);
        if (this.mSerialPort != null) {
            this.mSendingThread = new SendingThread(this, null);
            this.mSendingThread.start();
        }
    }

    /* access modifiers changed from: protected */
    public void onDataReceived(byte[] buffer, int size) {
        synchronized (this.mByteReceivedBackSemaphore) {
            for (int i = 0; i < size; i++) {
                if (buffer[i] != this.mValueToSend || this.mByteReceivedBack) {
                    this.mCorrupted = Integer.valueOf(this.mCorrupted.intValue() + 1);
                } else {
                    this.mByteReceivedBack = true;
                    this.mByteReceivedBackSemaphore.notify();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.mSendingThread != null) {
            this.mSendingThread.interrupt();
        }
        super.onDestroy();
    }
}

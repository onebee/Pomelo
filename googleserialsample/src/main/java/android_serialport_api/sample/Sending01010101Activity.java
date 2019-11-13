package android_serialport_api.sample;

import android.os.Bundle;
import java.io.IOException;
import java.util.Arrays;

public class Sending01010101Activity extends SerialPortActivity {
    byte[] mBuffer;
    SendingThread mSendingThread;

    private class SendingThread extends Thread {
        private SendingThread() {
        }

        /* synthetic */ SendingThread(Sending01010101Activity sending01010101Activity, SendingThread sendingThread) {
            this();
        }

        public void run() {
            while (!isInterrupted()) {
                try {
                    if (Sending01010101Activity.this.mOutputStream != null) {
                        Sending01010101Activity.this.mOutputStream.write(Sending01010101Activity.this.mBuffer);
                    } else {
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sending01010101);
        this.mBuffer = new byte[1024];
        Arrays.fill(this.mBuffer, (byte) 85);
        if (this.mSerialPort != null) {
            this.mSendingThread = new SendingThread(this, null);
            this.mSendingThread.start();
        }
    }

    /* access modifiers changed from: protected */
    public void onDataReceived(byte[] buffer, int size) {
    }
}

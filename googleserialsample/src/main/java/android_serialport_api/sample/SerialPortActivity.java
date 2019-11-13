package android_serialport_api.sample;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android_serialport_api.SerialPort;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidParameterException;

public abstract class SerialPortActivity extends Activity {
    protected Application mApplication;
    /* access modifiers changed from: private */
    public InputStream mInputStream;
    protected OutputStream mOutputStream;
    private ReadThread mReadThread;
    protected SerialPort mSerialPort;

    private class ReadThread extends Thread {
        private ReadThread() {
        }

        /* synthetic */ ReadThread(SerialPortActivity serialPortActivity, ReadThread readThread) {
            this();
        }

        public void run() {
            super.run();
            while (!isInterrupted()) {
                try {
                    byte[] buffer = new byte[64];
                    if (SerialPortActivity.this.mInputStream != null) {
                        int size = SerialPortActivity.this.mInputStream.read(buffer);
                        if (size > 0) {
                            SerialPortActivity.this.onDataReceived(buffer, size);
                        }
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
    public abstract void onDataReceived(byte[] bArr, int i);

    private void DisplayError(int resourceId) {
        Builder b = new Builder(this);
        b.setTitle("Error");
        b.setMessage(resourceId);
        b.setPositiveButton("OK", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                SerialPortActivity.this.finish();
            }
        });
        b.show();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mApplication = (Application) getApplication();
        try {
            this.mSerialPort = this.mApplication.getSerialPort();
            this.mOutputStream = this.mSerialPort.getOutputStream();
            this.mInputStream = this.mSerialPort.getInputStream();
            this.mReadThread = new ReadThread(this, null);
            this.mReadThread.start();
        } catch (SecurityException e) {
            DisplayError(R.string.error_security);
        } catch (IOException e2) {
            DisplayError(R.string.error_unknown);
        } catch (InvalidParameterException e3) {
            DisplayError(R.string.error_configuration);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.mReadThread != null) {
            this.mReadThread.interrupt();
        }
        this.mApplication.closeSerialPort();
        this.mSerialPort = null;
        super.onDestroy();
    }
}

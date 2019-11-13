package android_serialport_api.sample;

import android.content.SharedPreferences;
import android_serialport_api.SerialPort;
import android_serialport_api.SerialPortFinder;
import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;

public class Application extends android.app.Application {
    private SerialPort mSerialPort = null;
    public SerialPortFinder mSerialPortFinder = new SerialPortFinder();

    public SerialPort getSerialPort() throws SecurityException, IOException, InvalidParameterException {
        if (this.mSerialPort == null) {
            SharedPreferences sp = getSharedPreferences("android_serialport_api.sample_preferences", 0);
            String path = sp.getString("DEVICE", "");
            int baudrate = Integer.decode(sp.getString("BAUDRATE", "-1")).intValue();
            if (path.length() == 0 || baudrate == -1) {
                throw new InvalidParameterException();
            }
            this.mSerialPort = new SerialPort(new File(path), baudrate, 0);
        }
        return this.mSerialPort;
    }

    public void closeSerialPort() {
        if (this.mSerialPort != null) {
            this.mSerialPort.close();
            this.mSerialPort = null;
        }
    }
}

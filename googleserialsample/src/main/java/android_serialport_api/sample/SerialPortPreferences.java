package android_serialport_api.sample;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import android_serialport_api.SerialPortFinder;

public class SerialPortPreferences extends PreferenceActivity {
    private Application mApplication;
    private SerialPortFinder mSerialPortFinder;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mApplication = (Application) getApplication();
        this.mSerialPortFinder = this.mApplication.mSerialPortFinder;
        addPreferencesFromResource(R.xml.serial_port_preferences);

        ListPreference devices = (ListPreference) findPreference("DEVICE");
        String[] entries = this.mSerialPortFinder.getAllDevices();
        String[] entryValues = this.mSerialPortFinder.getAllDevicesPath();
        devices.setEntries(entries);
        devices.setEntryValues(entryValues);
        devices.setSummary(devices.getValue());

        devices.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                preference.setSummary((String) newValue);
                return true;
            }
        });


        PreferenceManager.getDefaultSharedPreferences(mApplication).edit().putString("BAUDRATE","38400").apply();

        ListPreference baudrates = (ListPreference) findPreference("BAUDRATE");
        baudrates.setSummary(baudrates.getValue());
        baudrates.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                preference.setSummary((String) newValue);
                return true;
            }
        });
    }
}

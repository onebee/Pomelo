package android_serialport_api.sample;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainMenu extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ((Button) findViewById(R.id.ButtonSetup)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                MainMenu.this.startActivity(new Intent(MainMenu.this, SerialPortPreferences.class));
            }
        });
        ((Button) findViewById(R.id.ButtonConsole)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                MainMenu.this.startActivity(new Intent(MainMenu.this, ConsoleActivity.class));
            }
        });
        ((Button) findViewById(R.id.ButtonLoopback)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                MainMenu.this.startActivity(new Intent(MainMenu.this, LoopbackActivity.class));
            }
        });
        ((Button) findViewById(R.id.Button01010101)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                MainMenu.this.startActivity(new Intent(MainMenu.this, Sending01010101Activity.class));
            }
        });
        ((Button) findViewById(R.id.ButtonAbout)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Builder builder = new Builder(MainMenu.this);
                builder.setTitle("About");
                builder.setMessage(R.string.about_msg);
                builder.show();
            }
        });
        ((Button) findViewById(R.id.ButtonQuit)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                MainMenu.this.finish();
            }
        });
    }
}

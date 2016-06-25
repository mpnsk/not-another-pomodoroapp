package com.github.mpnsk.notanotherpomodoroapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button start = (Button) findViewById(R.id.button_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupAlarm();
            }
        });
    }

    private void setupAlarm() {
        Intent intent = new Intent(this, DummyActivity.class);
        PendingIntent alarmIntent = PendingIntent.getActivity(this, 0, intent, 0);

        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + 5 * 1000,
                alarmIntent);
    }

}

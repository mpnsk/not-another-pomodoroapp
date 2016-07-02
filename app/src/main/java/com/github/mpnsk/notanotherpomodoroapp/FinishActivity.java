package com.github.mpnsk.notanotherpomodoroapp;

import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;

public class FinishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        v.vibrate(1000);
    }
}

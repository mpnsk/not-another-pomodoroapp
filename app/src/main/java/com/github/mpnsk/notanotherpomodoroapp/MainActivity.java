package com.github.mpnsk.notanotherpomodoroapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView mTextField;
    private long endPoint;
    private int duration;
    private MyCountDownTimer countDownTimer;
    private String tag = "lifecycle";
    private long startPoint;

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


        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        if (duration != 0) {
            progressBar.setMax(duration);
        }

        mTextField = (TextView) findViewById(R.id.time_remaining);
        Button increment = (Button) findViewById(R.id.button_increment);
        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.incrementProgressBy(1);
            }
        });
    }

    private void setupAlarm() {
        duration = 30;
        progressBar.setMax(duration);

        startPoint = SystemClock.elapsedRealtime();

        if(countDownTimer != null){
            countDownTimer.cancel();
            countDownTimer=null;
        }
        countDownTimer = new MyCountDownTimer(duration * 1000);
        countDownTimer.setmTextField(mTextField);
        countDownTimer.setProgressBar(progressBar);
        countDownTimer.start();

        Intent finish = new Intent(this, FinishActivity.class);
        PendingIntent alarmIntent = PendingIntent.getActivity(this, 0, finish, 0);
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + duration * 1000,
                alarmIntent);

    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d(tag, "stop!");

        if(countDownTimer != null && startPoint != 0) {
            countDownTimer.cancel();
            countDownTimer = null;
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        if(countDownTimer == null && startPoint != 0){
            long millisSinceStart = SystemClock.elapsedRealtime() - startPoint;
            long millisLeft = duration * 1000 - millisSinceStart;
            countDownTimer = new MyCountDownTimer(millisLeft);
            countDownTimer.setmTextField(mTextField);
            countDownTimer.setProgressBar(progressBar);
            countDownTimer.start();
        }
    }
}

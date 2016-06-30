package com.github.mpnsk.notanotherpomodoroapp;

import android.os.CountDownTimer;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;


class MyCountDownTimer extends CountDownTimer {

    ProgressBar progressBar;
    private String tag = "lifecycle";

    public void setmTextField(TextView mTextField) {
        this.mTextField = mTextField;
    }

    TextView mTextField;

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }


    public MyCountDownTimer(long millisUntilFinished) {
        super(millisUntilFinished, 1000);

    }


    public void onTick(long millisUntilFinished) {

        mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);

        progressBar.setProgress((int) millisUntilFinished / 1000);
        Log.d(tag, "remaining: " + millisUntilFinished);

    }

    public void onFinish() {
        mTextField.setText("finished?");
        Log.d(tag, "finished!");
    }
}

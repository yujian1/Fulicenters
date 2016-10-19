package com.example.lenovo.fulicenters.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lenovo.fulicenters.R;
import com.example.lenovo.fulicenters.utils.MFGT;

public class SplashActivity extends AppCompatActivity {
    private final long sleepTime = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Thread(new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                //Creat db
                long costTime = System.currentTimeMillis() - start;
                if (sleepTime - costTime > 0) {
                    try {
                        Thread.sleep(sleepTime - costTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    //startActivity(intent);
                    //finish();
                    MFGT.gotoMainActivity(SplashActivity.this);
                    MFGT.finish(SplashActivity.this);
                }
            }
        }).start();
    }
}

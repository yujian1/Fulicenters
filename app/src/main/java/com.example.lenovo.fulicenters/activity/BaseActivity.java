package com.example.lenovo.fulicenters.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.lenovo.fulicenters.utils.MFGT;

/**
 * Created by lenovo on 2016/10/19.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        setListener();
    }
    protected abstract void initView();
    protected  abstract void initData();
    protected abstract  void setListener();

    public  void onBackPressed(){
        MFGT.finish(this);
    }

}

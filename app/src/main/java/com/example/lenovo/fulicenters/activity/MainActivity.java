package com.example.lenovo.fulicenters.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.lenovo.fulicenters.R;
import com.example.lenovo.fulicenters.utils.L;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.newGoods)
    RadioButton newGoods;
    @BindView(R.id.boutique)
    RadioButton boutique;
    @BindView(R.id.category)
    RadioButton category;
    @BindView(R.id.layout_cart)
    RadioButton layoutCart;
    @BindView(R.id.tvCart)
    TextView tvCart;
    @BindView(R.id.personal)
    RadioButton personal;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        L.i("MainActivity onCreate");
    }


    public void onClick(View view) {
    }
}

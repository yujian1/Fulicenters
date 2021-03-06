package com.example.lenovo.fulicenters.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.lenovo.fulicenters.R;
import com.example.lenovo.fulicenters.fragment.BoutiqueFragment;
import com.example.lenovo.fulicenters.fragment.CategoryFragment;
import com.example.lenovo.fulicenters.fragment.NewGoodsFragment;
import com.example.lenovo.fulicenters.utils.L;


import butterknife.BindView;
import butterknife.ButterKnife;
public class MainActivity extends BaseActivity  {


    @BindView(R.id.layout_new_good)
    RadioButton mlayoutNewGood;
    @BindView(R.id.layout_boutique)
    RadioButton mlayoutBoutique;
    @BindView(R.id.layout_category)
    RadioButton mlayoutCategory;
    @BindView(R.id.layout_cart)
    RadioButton mlayoutCart;
    @BindView(R.id.tvCartHint)
    TextView mtvCartHint;
    @BindView(R.id.layout_personal_center)
    RadioButton mlayoutPersonalCenter;
    int index;
    int currentIndex;
    RadioButton[] rbs;
    Fragment[]mFragments;
    NewGoodsFragment mNewGoodsFragment;
    BoutiqueFragment mBoutiqueFragment;
    CategoryFragment mCategoryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        L.i("MainActivity onCreate");
        super.onCreate(savedInstanceState);
    }

    private void initFragment() {
        mFragments=new Fragment[5];
        mNewGoodsFragment=new NewGoodsFragment();
        mBoutiqueFragment=new BoutiqueFragment();
        mCategoryFragment=new CategoryFragment();
        mFragments[0]=mNewGoodsFragment;
        mFragments[1]=mBoutiqueFragment;
        mFragments[2]=mCategoryFragment;
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container,mNewGoodsFragment)
                .add(R.id.fragment_container,mBoutiqueFragment)
                .add(R.id.fragment_container,mCategoryFragment)
                .hide(mBoutiqueFragment)
                .hide(mCategoryFragment)
                .show(mNewGoodsFragment)
                .commit();
    }


    @Override
    protected  void initView() {
        rbs = new RadioButton[5];
        rbs[0] = mlayoutNewGood;
        rbs[1] = mlayoutBoutique;
        rbs[2] = mlayoutCategory;
        rbs[3] = mlayoutCart;
        rbs[4] = mlayoutPersonalCenter;

    }

    @Override
    protected void initData() {
        initFragment();


    }

    @Override
    protected void setListener() {

    }

    public void onCheckedChange(View v) {
        switch (v.getId()) {
            case R.id.layout_new_good:
                index = 0;
                break;
            case R.id.layout_boutique:
                index = 1;
                break;
            case R.id.layout_category:
                index = 2;
                break;
            case R.id.layout_cart:
                index = 3;
                break;
            case R.id.layout_personal_center:
                index = 4;
                break;
        }
        setFragment();

    }

    private void setFragment() {
        if (index==currentIndex){
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.hide(mFragments[currentIndex]);
            if (!mFragments[index].isAdded()){
                ft.add(R.id.fragment_container,mFragments[index]);

            }
            ft.show(mFragments[index]).commit();
        }
        setRadioButtonStatus();
        currentIndex=index;
    }

    private void setRadioButtonStatus() {
        for (int i = 0; i < rbs.length; i++) {
            if (i == index) {
                rbs[i].setChecked(true);

            } else {
                rbs[i].setChecked(false);
            }
        }
    }
    public  void onBackPressed(){

        finish();
    }

}

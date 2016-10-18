package com.example.lenovo.fulicenters.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.lenovo.fulicenters.I;
import com.example.lenovo.fulicenters.R;
import com.example.lenovo.fulicenters.bean.GoodsDetailsBean;
import com.example.lenovo.fulicenters.net.NetDao;
import com.example.lenovo.fulicenters.net.OkHttpUtils;
import com.example.lenovo.fulicenters.utils.CommonUtils;
import com.example.lenovo.fulicenters.utils.L;
import com.example.lenovo.fulicenters.view.FlowIndicator;
import com.example.lenovo.fulicenters.view.SlideAutoLoopView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsDetailActivity extends AppCompatActivity {

    @BindView(R.id.backClickArea)
    LinearLayout mBackClickArea;
    @BindView(R.id.tv_good_name_english)
    TextView mTvGoodNameEnglish;
    @BindView(R.id.tv_good_name)
    TextView mTvGoodName;
    @BindView(R.id.tv_good_price_shop)
    TextView mTvGoodPriceShop;
    @BindView(R.id.tv_good_price_current)
    TextView mTvGoodPriceCurrent;
    @BindView(R.id.salv)
    SlideAutoLoopView mSalv;
    @BindView(R.id.indicator)
    FlowIndicator mIndicator;
    @BindView(R.id.wv_good_brief)
    WebView mWvGoodBrief;
    int goodsId;

    GoodsDetailActivity mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        ButterKnife.bind(this);
        getIntent().putExtra(I.GoodsDetails.KEY_GOODS_ID,0);
        if (goodsId==0){
            finish();
        }
        mContext=this;
        inintView();
        initDeta();
        setListener();
    }

    private void inintView() {
    }
    private void initDeta() {
        NetDao.downloadGoodsDetail(mContext, goodsId, (OkHttpUtils.OnCompleteListener<GoodsDetailsBean>) new OkHttpUtils.OnCompleteListener<GoodsDetailsBean>() {
            @Override
            public void onSuccess(GoodsDetailsBean result) {
                L.i("details="+result);
                if (result!=null){
                    showGoodDetails(result);
                }else {
                    finish();
                }
            }

            @Override
            public void onError(String error) {
                finish();
                L.d("details,error="+error);
                CommonUtils.showShortToast(error);
            }
        });
    }

    private void showGoodDetails(GoodsDetailsBean details) {
        mTvGoodNameEnglish.setText(details.getGoodsEnglishName());
        mTvGoodName.setText(details.getGoodsName());
        mTvGoodPriceCurrent.setText(details.getCurrencyPrice());
        mTvGoodPriceShop.setText(details.getShopPrice());


    }


    private void setListener() {
        return;
    }
}

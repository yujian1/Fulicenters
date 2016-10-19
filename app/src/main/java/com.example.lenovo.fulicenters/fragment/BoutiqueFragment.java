package com.example.lenovo.fulicenters.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.fulicenters.I;
import com.example.lenovo.fulicenters.R;
import com.example.lenovo.fulicenters.activity.MainActivity;
import com.example.lenovo.fulicenters.adapter.BoutiqueAdapter;
import com.example.lenovo.fulicenters.bean.BoutiqueBean;
import com.example.lenovo.fulicenters.net.NetDao;
import com.example.lenovo.fulicenters.net.OkHttpUtils;
import com.example.lenovo.fulicenters.utils.CommonUtils;
import com.example.lenovo.fulicenters.utils.ConvertUtils;
import com.example.lenovo.fulicenters.utils.L;
import com.example.lenovo.fulicenters.view.SpaceItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2016/10/19.
 */

public class BoutiqueFragment extends Fragment {
    @BindView(R.id.tvrefresh)
    TextView mtvrefresh;
    @BindView(R.id.rv)
    RecyclerView mrv;
    @BindView(R.id.srl)
    SwipeRefreshLayout msrl;
    LayoutManager llm;
    MainActivity mContext;
    BoutiqueAdapter mAdapter;
   ArrayList<BoutiqueBean> mList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_newgoods, container, false);
        ButterKnife.bind(this, layout);
        mContext= (MainActivity) getContext();
        mAdapter =new BoutiqueAdapter(mContext,mList);
        initView();
        initData();
        return layout;
    }

    private void initData() {
        downloadBoutipue(I.ACTION_DOWNLOAD);

    }
    private void downloadBoutipue(final int action){
        NetDao.downloadBoutique(mContext, new OkHttpUtils.OnCompleteListener<BoutiqueBean[]>() {
            @Override
            public void onSuccess(BoutiqueBean[] result) {
                msrl.setRefreshing(false);
                mtvrefresh.setVisibility(View.GONE);
                mAdapter.setMore(true);
                L.e("result="+result);
                if(result!=null && result.length>0){
                ArrayList<BoutiqueBean> list= ConvertUtils.array2List(result);
                    if (action== I.ACTION_DOWNLOAD ||action==I.ACTION_PULL_DOWN){
                        mAdapter.initData(list);
                    }else {
                        mAdapter.addData(list);
                    }
                    mAdapter.initData(list);
                    if (list.size()<I.PAGE_SIZE_DEFAULT){
                        mAdapter.setMore(false);
                    }
                }else {
                    mAdapter.setMore(false);
                }
            }

            @Override
            public void onError(String error) {
                msrl.setRefreshing(false);
                mtvrefresh.setVisibility(View.GONE);
                mAdapter.setMore(false);
                CommonUtils.showShortToast(error);
                L.e("error"+error);

            }
        });

    }

    private void initView() {
        msrl.setColorSchemeColors(
                getResources().getColor(R.color.google_blue),
                getResources().getColor(R.color.google_green),
                getResources().getColor(R.color.google_red),
                getResources().getColor(R.color.google_yellow)
        );
        llm=new LinearLayoutManager(mContext);
        mrv.setLayoutManager(llm);
        mrv.setHasFixedSize(true);
        mrv.setAdapter(mAdapter);
        mrv.addItemDecoration(new SpaceItemDecoration(12));

    }

}

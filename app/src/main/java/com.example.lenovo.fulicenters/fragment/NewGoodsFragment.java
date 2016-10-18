package com.example.lenovo.fulicenters.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.lenovo.fulicenters.I;
import com.example.lenovo.fulicenters.R;
import com.example.lenovo.fulicenters.activity.MainActivity;
import com.example.lenovo.fulicenters.adapter.GoodsAdapter;
import com.example.lenovo.fulicenters.bean.NewGoodsBean;
import com.example.lenovo.fulicenters.net.NetDao;
import com.example.lenovo.fulicenters.net.OkHttpUtils;
import com.example.lenovo.fulicenters.utils.CommonUtils;
import com.example.lenovo.fulicenters.utils.ConvertUtils;
import com.example.lenovo.fulicenters.utils.L;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * Created by lenovo on 2016/10/17.
 */
public class NewGoodsFragment extends Fragment {
    @BindView(R.id.tvrefresh)
    TextView mtvrefresh;
    @BindView(R.id.rv)
    RecyclerView mrv;
    @BindView(R.id.srl)
    SwipeRefreshLayout msrl;
     MainActivity mContext;
    GoodsAdapter mAdapter;
    ArrayList<NewGoodsBean> mList;
    int pageId=1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_newgoods, container, false);
        ButterKnife.bind(this, layout);
        mContext= (MainActivity) getContext();
        mList=new ArrayList<>();
        mAdapter=new GoodsAdapter(mContext,mList);
        initView();
        initData();
        return layout;

    }

    private void initData() {
        NetDao.downloadNewGoods(mContext, pageId, new OkHttpUtils.OnCompleteListener<NewGoodsBean[]>() {
            @Override
            public void onSuccess(NewGoodsBean[] result) {
                msrl.setRefreshing(false);
                mtvrefresh.setVisibility(View.GONE);
                mAdapter.setMore(true);
                L.e("result="+result);
              if(result!=null && result.length>0){
                  ArrayList<NewGoodsBean> list = ConvertUtils.array2List(result);
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
        GridLayoutManager glm=new GridLayoutManager(mContext, I.COLUM_NUM);
        mrv.setLayoutManager(glm);
        mrv.setHasFixedSize(true);
        mrv.setAdapter(mAdapter);

    }
}

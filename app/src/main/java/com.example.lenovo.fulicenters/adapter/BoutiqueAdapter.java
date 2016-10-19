package com.example.lenovo.fulicenters.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lenovo.fulicenters.R;
import com.example.lenovo.fulicenters.bean.BoutiqueBean;
import com.example.lenovo.fulicenters.utils.ImageLoader;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2016/10/19.
 */

public class BoutiqueAdapter extends Adapter<BoutiqueAdapter.BoutqueViewHolder> {
    Context mCoutext;
    ArrayList<BoutiqueBean> mList;


    public BoutiqueAdapter (ArrayList<BoutiqueBean> list, Context context) {
        mCoutext=context;
        mList=new ArrayList<>();
        mList.addAll(list);
    }
    @Override
    public BoutqueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BoutqueViewHolder holder = new BoutqueViewHolder(LayoutInflater.from(mCoutext)
                    .inflate(R.layout.item_boutique, parent, false));

        return holder;
    }
    @Override
    public void onBindViewHolder(BoutqueViewHolder holder, int position) {

            BoutiqueBean boutiqueBean = mList.get(position);
            ImageLoader.downloadImg(mCoutext,holder.mivBoutiqueImg,boutiqueBean.getImageurl());
            holder.mtvBoutiqueTitle.setText(boutiqueBean.getTitle());
            holder.mtvBoutiqueName.setText(boutiqueBean.getName());
            holder.mtvBoutiqueDescription.setText(boutiqueBean.getDescription());
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size()  : 0;

    }


    public void initData(ArrayList<BoutiqueBean> list) {
        if (mList!=null){
            mList.clear();
        }
        mList.addAll(list);

        notifyDataSetChanged();
    }


    class BoutqueViewHolder extends ViewHolder {
        @BindView(R.id.ivBoutiqueImg)
        ImageView mivBoutiqueImg;
        @BindView(R.id.tvBoutiqueTitle)
        TextView mtvBoutiqueTitle;
        @BindView(R.id.tvBoutiqueName)
        TextView mtvBoutiqueName;
        @BindView(R.id.tvBoutiqueDescription)
        TextView mtvBoutiqueDescription;
        @BindView(R.id.layout_boutique_item)
        RelativeLayout mlayoutBoutiqueItem;

         BoutqueViewHolder(View view) {
             super(view);
            ButterKnife.bind(this, view);
        }
    }
}

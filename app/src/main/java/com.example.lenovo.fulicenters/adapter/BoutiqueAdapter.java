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

import com.example.lenovo.fulicenters.I;
import com.example.lenovo.fulicenters.R;
import com.example.lenovo.fulicenters.bean.BoutiqueBean;
import com.example.lenovo.fulicenters.utils.ImageLoader;
import com.example.lenovo.fulicenters.view.FooterViewHolder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2016/10/19.
 */

public class BoutiqueAdapter extends Adapter {
    Context mCoutext;
    ArrayList<BoutiqueBean> mList;

    boolean isMore;

    public BoutiqueAdapter(ArrayList<BoutiqueBean> list, Context Coutext) {
        mCoutext=Coutext;
        mList=new ArrayList<>();
        mList.addAll(list);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = null;
        if (viewType == I.TYPE_FOOTER) {
            holder = new FooterViewHolder(LayoutInflater.from(mCoutext)
                    .inflate(R.layout.item_footer, parent, false));
        } else {
            holder = new BoutqueViewHolder(LayoutInflater.from(mCoutext)
                    .inflate(R.layout.item_boutique, parent, false));
        }
        return holder;
    }

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean more) {
        isMore = more;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder instanceof FooterViewHolder){
            ((FooterViewHolder) holder).mTvFooter.setText(getFooterString());

        }
        if (holder instanceof  BoutqueViewHolder){
            BoutiqueBean boutiqueBean = mList.get(position);
            ImageLoader.downloadImg(mCoutext,((BoutqueViewHolder) holder).mivBoutiqueImg,boutiqueBean.getImageurl());
            ((BoutqueViewHolder) holder).mtvBoutiqueTitle.setText(boutiqueBean.getTitle());
            ((BoutqueViewHolder) holder).mtvBoutiqueName.setText(boutiqueBean.getName());
            ((BoutqueViewHolder) holder).mtvBoutiqueDescription.setText(boutiqueBean.getDescription());

        }

    }

    private int getFooterString() {
        return isMore?R.string.load_more:R.string.no_more;
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() + 1 : 1;

    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return I.TYPE_FOOTER;
        }
        return I.TYPE_ITEM;
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

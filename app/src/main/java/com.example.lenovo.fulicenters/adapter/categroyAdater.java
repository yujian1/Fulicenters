package com.example.lenovo.fulicenters.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lenovo.fulicenters.R;
import com.example.lenovo.fulicenters.bean.CategoryChildBean;
import com.example.lenovo.fulicenters.bean.CategoryGroupBean;
import com.example.lenovo.fulicenters.utils.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2016/10/20.
 */

public class CategroyAdater extends BaseExpandableListAdapter{
    Context mcontext;
    ArrayList<CategoryGroupBean> mGrouplist;
    ArrayList<ArrayList<CategoryChildBean>> mChildList;

    public CategroyAdater(Context context, ArrayList<CategoryGroupBean> Grouplist,
                          ArrayList<ArrayList<CategoryChildBean>> ChildList) {
        mcontext = context;
        mGrouplist = new ArrayList<>();
        mGrouplist.addAll(Grouplist);
        mChildList = new ArrayList<>();
        mChildList.addAll(ChildList);
    }

    @Override
    public int getGroupCount() {
        return mGrouplist != null ? mGrouplist.size() : 0;
    }

    @Override
    public int getChildrenCount(int groupProsition) {
        return mChildList != null && mChildList.get(groupProsition) != null ?
                mChildList.get(groupProsition).size() : 0;
    }

    @Override
    public CategoryGroupBean getGroup(int groupProsition) {
        return mGrouplist != null ? mGrouplist.get(groupProsition) : null;
    }

    @Override
    public CategoryChildBean getChild(int groupProsition, int childProsition) {
        return mChildList != null && mChildList.get(groupProsition) != null ?
                mChildList.get(groupProsition).get(childProsition) : null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupProsition, boolean isExpand, View view, ViewGroup viewGroup) {
        GroupViewHolder holder;
        if (view == null) {
            view = View.inflate(mcontext, R.layout.item_category_group, null);
            holder = new GroupViewHolder(view);
            view.setTag(holder);

        } else {
            view.getTag();
            holder = (GroupViewHolder) view.getTag();
        }
        CategoryGroupBean group = mGrouplist.get(groupProsition);
        if (group != null) {
            ImageLoader.downloadImg(mcontext, holder.mivGroupThumb, group.getImageUrl());
            holder.mtvGroupName.setText(group.getName());
            holder.mivIndicator.setImageResource(isExpand ? R.mipmap.expand_off : R.mipmap.expand_on);

        }

        return view;
    }

    @Override
    public View getChildView(int groupProsition, int childProsition, boolean b, View view, ViewGroup viewGroup) {
        ChildViewHolder holder;
        if (view == null) {
            view = View.inflate(mcontext, R.layout.item_category_child, null);
            holder=new ChildViewHolder(view);
            view.setTag(holder);

        } else {
            holder= (ChildViewHolder) view.getTag();

        }
        CategoryChildBean child = getChild(groupProsition, childProsition);
        if (child!=null){
            ImageLoader.downloadImg(mcontext,holder.mivCategroryChildThumb,child.getImageUrl());
            holder.mtvCategroyChildName.setText(child.getName());
        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    public void initData(ArrayList<CategoryGroupBean> GroupList,
                         ArrayList<ArrayList<CategoryChildBean>> ChildList) {
        if (mChildList!=null){
           mGrouplist.clear();
        }
        mGrouplist.addAll(GroupList);
        if (mChildList!=null){
            mChildList.clear();
        }
        mChildList.addAll(ChildList);
        notifyDataSetChanged();
    }


    class GroupViewHolder {
        @BindView(R.id.iv_group_thumb)
        ImageView mivGroupThumb;
        @BindView(R.id.tv_group_name)
        TextView mtvGroupName;
        @BindView(R.id.iv_indicator)
        ImageView mivIndicator;

        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    class ChildViewHolder {
        @BindView(R.id.iv_categrory_child_thumb)
        ImageView mivCategroryChildThumb;
        @BindView(R.id.tv_categroy_child_name)
        TextView mtvCategroyChildName;
        @BindView(R.id.layout_category_child)
        RelativeLayout mlayoutCategoryChild;

        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

package com.example.lenovo.fulicenters.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.lenovo.fulicenters.R;
import com.example.lenovo.fulicenters.activity.MainActivity;
import com.example.lenovo.fulicenters.adapter.CategroyAdater;
import com.example.lenovo.fulicenters.bean.CategoryChildBean;
import com.example.lenovo.fulicenters.bean.CategoryGroupBean;
import com.example.lenovo.fulicenters.net.NetDao;
import com.example.lenovo.fulicenters.net.OkHttpUtils;
import com.example.lenovo.fulicenters.utils.ConvertUtils;
import com.example.lenovo.fulicenters.utils.L;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2016/10/20.
 */

public class CategoryFragment extends BaseFragment {

    @BindView(R.id.elv_category)
    ExpandableListView melvCategory;
    CategroyAdater mAdapter;
    MainActivity mContext;
    ArrayList<CategoryGroupBean> mGroupList;
    ArrayList<ArrayList<CategoryChildBean>> mChildList;
    int groupCount;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_category, container, false);

        mContext= (MainActivity) getContext();
        mGroupList=new ArrayList<>();
        mChildList=new ArrayList<>();
        mAdapter=new CategroyAdater(mContext,mGroupList,mChildList);
        ButterKnife.bind(this, layout);
        super.onCreateView(inflater, container, savedInstanceState);

        return layout;
    }


    @Override
    protected void initView() {
        melvCategory.setGroupIndicator(null);
        melvCategory.setAdapter(mAdapter);


    }

    @Override
    protected void initData() {
     downloadGroup();

    }

    private void downloadGroup() {

        NetDao.downloadGroup(mContext, new OkHttpUtils.OnCompleteListener<CategoryGroupBean[]>() {
            @Override
            public void onSuccess(CategoryGroupBean[] result) {
                L.e("downloadGroup"+result);

                if (result!=null&& result.length>0){
                    ArrayList<CategoryGroupBean> groupList = ConvertUtils.array2List(result);
                    L.e("groupList"+"  "+groupList.size());
                    mGroupList.addAll(groupList);
                    for (CategoryGroupBean g:groupList){
                        downloadChild(g.getId());
                    }
                }
            }

            @Override
            public void onError(String error) {
                L.e("error"+error);

            }
        });
    }

    private void downloadChild(int id) {
        NetDao.downloadchild(mContext, id, new OkHttpUtils.OnCompleteListener<CategoryChildBean[]>() {
            @Override
            public void onSuccess(CategoryChildBean[] result) {

                groupCount++;
                L.e("downloadGroup"+result);
                if (result!=null&& result.length>0){
                    ArrayList<CategoryChildBean> childList = ConvertUtils.array2List(result);
                    L.e("groupList"+"  "+childList.size());
                    mChildList.add(childList);
                }
                if (groupCount==mGroupList.size()){
                    mAdapter.initData(mGroupList,mChildList);
                }
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    @Override
    protected void setListener() {

    }
}

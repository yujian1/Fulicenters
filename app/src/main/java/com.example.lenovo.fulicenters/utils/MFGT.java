package com.example.lenovo.fulicenters.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.lenovo.fulicenters.I;
import com.example.lenovo.fulicenters.R;
import com.example.lenovo.fulicenters.activity.BoutiqueChildActivity;
import com.example.lenovo.fulicenters.activity.CategoryChildActivity;
import com.example.lenovo.fulicenters.activity.GoodsDetailActivity;
import com.example.lenovo.fulicenters.activity.MainActivity;
import com.example.lenovo.fulicenters.bean.BoutiqueBean;

public class MFGT {
    public static void finish(Activity activity){
        activity.finish();
        activity.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
    }
    public static void gotoMainActivity(Activity context){
        startActivity(context, MainActivity.class);
    }
    public static void startActivity(Activity context,Class<?> cls){
        Intent intent = new Intent();
        intent.setClass(context,cls);
        startActivity(context,intent);
    }
    public static void gotoGoodsDetailsActivity(Context context, int goodsId) {
        Intent intent = new Intent();
        intent.setClass(context, GoodsDetailActivity.class);
        intent.putExtra(I.GoodsDetails.KEY_GOODS_ID, goodsId);
        startActivity(context,intent);
    }
    public  static void startActivity(Context context,Intent intent){
        context.startActivity(intent);
        ( (Activity) context).overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);

    }
    public static void gotoBoutiqueChildActivity(Context context, BoutiqueBean bean) {
        Intent intent = new Intent();
        intent.setClass(context, BoutiqueChildActivity.class);
        intent.putExtra(I.Boutique.CAT_ID, bean);
        startActivity(context,intent);
    }
    public static void gotoCategoryChildActivity(Context context, int catId) {
        Intent intent = new Intent();
        intent.setClass(context, CategoryChildActivity.class);
        intent.putExtra(I.CategoryChild.CAT_ID, catId);
        startActivity(context,intent);
    }

}

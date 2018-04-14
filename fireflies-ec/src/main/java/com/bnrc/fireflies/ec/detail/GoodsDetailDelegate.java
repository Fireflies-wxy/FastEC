package com.bnrc.fireflies.ec.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bnrc.fireflies.delegates.FirefliesDelegate;
import com.bnrc.fireflies.ec.R;

/**
 * Created by apple on 2018/4/14.
 */

public class GoodsDetailDelegate extends FirefliesDelegate{

    public static GoodsDetailDelegate create(){
        return new GoodsDetailDelegate();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}

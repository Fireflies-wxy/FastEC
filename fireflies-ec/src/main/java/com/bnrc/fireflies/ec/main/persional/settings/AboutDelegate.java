package com.bnrc.fireflies.ec.main.persional.settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.bnrc.fireflies.app.Fireflies;
import com.bnrc.fireflies.delegates.FirefliesDelegate;
import com.bnrc.fireflies.ec.R;
import com.bnrc.fireflies.ec.R2;
import com.bnrc.fireflies.net.RestClient;
import com.bnrc.fireflies.net.callBack.ISuccess;

import butterknife.BindView;

/**
 * Created by apple on 2018/4/30.
 */

public class AboutDelegate extends FirefliesDelegate {

    @BindView(R2.id.tv_info)
    AppCompatTextView mTextView = null;


    @Override
    public Object setLayout() {
        return R.layout.delegate_about;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        RestClient.builder()
                .url("about")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final String info = JSON.parseObject(response).getString("data");
                        mTextView.setText(info);
                    }
                })
                .build()
                .get();
    }
}

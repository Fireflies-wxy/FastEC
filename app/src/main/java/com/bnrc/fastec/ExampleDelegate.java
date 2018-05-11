package com.bnrc.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.bnrc.fastec.R;
import com.bnrc.fireflies.delegates.FirefliesDelegate;
import com.bnrc.fireflies.net.RestClient;
import com.bnrc.fireflies.net.callBack.IError;
import com.bnrc.fireflies.net.callBack.IFailure;
import com.bnrc.fireflies.net.callBack.ISuccess;

/**
 * Created by apple on 2018/4/7.
 */

public class ExampleDelegate extends FirefliesDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    private void testRestClient(){
        RestClient.builder()
                .url("http://58.87.87.220/index")
                .params("","")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}

package com.bnrc.fireflies.ec.main.persional.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bnrc.fireflies.delegates.FirefliesDelegate;
import com.bnrc.fireflies.ec.R;

/**
 * Created by apple on 2018/4/30.
 */

public class NameDelegate extends FirefliesDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_name;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}

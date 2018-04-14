package com.bnrc.fireflies.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.bnrc.fireflies.delegates.bottom.BottomItemDelegate;
import com.bnrc.fireflies.ec.R;

/**
 * Created by apple on 2018/4/12.
 */

public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
    }
}

package com.bnrc.fireflies.ui.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by apple on 2018/4/13.
 */

public class MultipleViewHolder extends BaseViewHolder {
    private MultipleViewHolder(View view) {
        super(view);
    }

    public static MultipleViewHolder create(View view) {
        return new MultipleViewHolder(view);
    }
}

package com.bnrc.fireflies.ec.main.index;

import android.view.View;

import com.bnrc.fireflies.app.Fireflies;
import com.bnrc.fireflies.delegates.FirefliesDelegate;
import com.bnrc.fireflies.ec.detail.GoodsDetailDelegate;
import com.bnrc.fireflies.ui.recycler.MultipleFields;
import com.bnrc.fireflies.ui.recycler.MultipleItemEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

/**
 * Created by apple on 2018/4/14.
 */

public class IndexItemClickListener extends SimpleClickListener{


    private final FirefliesDelegate DELEGATE;

    private IndexItemClickListener(FirefliesDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static SimpleClickListener create(FirefliesDelegate delegate) {
        return new IndexItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
        final int goodsId = entity.getField(MultipleFields.ID);
        final GoodsDetailDelegate delegate = GoodsDetailDelegate.create(goodsId);
        DELEGATE.getSupportDelegate().start(delegate);
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
    }
}

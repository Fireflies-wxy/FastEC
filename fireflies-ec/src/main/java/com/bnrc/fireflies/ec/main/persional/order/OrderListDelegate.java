package com.bnrc.fireflies.ec.main.persional.order;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bnrc.fireflies.delegates.FirefliesDelegate;
import com.bnrc.fireflies.ec.R;
import com.bnrc.fireflies.ec.R2;
import com.bnrc.fireflies.ec.main.persional.PersonalDelegate;
import com.bnrc.fireflies.net.RestClient;
import com.bnrc.fireflies.net.callBack.ISuccess;
import com.bnrc.fireflies.ui.recycler.MultipleItemEntity;

import java.util.List;

import butterknife.BindView;

/**
 * Created by apple on 2018/4/30.
 */

public class OrderListDelegate extends FirefliesDelegate {


    private String mType = null;

    @BindView(R2.id.rv_order_list)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mType = args.getString(PersonalDelegate.ORDER_TYPE);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .loader(getContext())
                .url("order_list")
                .params("type", mType)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mRecyclerView.setLayoutManager(manager);
                        final List<MultipleItemEntity> data =
                                new OrderListDataConverter().setJsonData(response).convert();
                        final OrderListAdapter adapter = new OrderListAdapter(data);
                        mRecyclerView.setAdapter(adapter);
                        mRecyclerView.addOnItemTouchListener(new OrderListClickListener(OrderListDelegate.this));
                    }
                })
                .build()
                .get();
    }
}

package com.bnrc.fireflies.ec.main.persional.address;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bnrc.fireflies.app.Fireflies;
import com.bnrc.fireflies.delegates.FirefliesDelegate;
import com.bnrc.fireflies.ec.R;
import com.bnrc.fireflies.ec.R2;
import com.bnrc.fireflies.net.RestClient;
import com.bnrc.fireflies.net.callBack.ISuccess;
import com.bnrc.fireflies.ui.recycler.MultipleItemEntity;
import com.bnrc.fireflies.util.log.FirefliesLogger;

import java.util.List;

import butterknife.BindView;

import static com.blankj.utilcode.util.Utils.getContext;

/**
 * Created by apple on 2018/4/30.
 */

public class AddressDelegate extends FirefliesDelegate implements ISuccess {

    @BindView(R2.id.rv_address)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_address;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        RestClient.builder()
                .url("address")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        FirefliesLogger.d("AddressDelegate", response);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final List<MultipleItemEntity> data =
                new AddressDataConverter().setJsonData(response).convert();
        final AddressAdapter addressAdapter = new AddressAdapter(data);
        mRecyclerView.setAdapter(addressAdapter);
    }
}

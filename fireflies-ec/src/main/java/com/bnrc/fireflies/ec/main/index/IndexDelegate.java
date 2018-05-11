package com.bnrc.fireflies.ec.main.index;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bnrc.fireflies.delegates.bottom.BottomItemDelegate;
import com.bnrc.fireflies.ec.R;
import com.bnrc.fireflies.ec.R2;
import com.bnrc.fireflies.ec.main.EcBottomDelegate;
import com.bnrc.fireflies.net.RestClient;
import com.bnrc.fireflies.net.callBack.ISuccess;
import com.bnrc.fireflies.ui.recycler.BaseDecoration;
import com.bnrc.fireflies.ui.recycler.MultipleFields;
import com.bnrc.fireflies.ui.recycler.MultipleItemEntity;
import com.bnrc.fireflies.ui.refresh.RefreshHandler;
import com.joanzapata.iconify.widget.IconTextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by apple on 2018/4/12.
 */

public class IndexDelegate extends BottomItemDelegate{
    private static final String TAG = "IndexDelegate";

    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView = null;

    private RefreshHandler mRefreshHandler = null;

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mRefreshHandler = RefreshHandler.create(mRefreshLayout, mRecyclerView, new IndexDataConverter());
        RestClient.builder()
                .url("index")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final IndexDataConverter converter = new IndexDataConverter();
                        converter.setJsonData(response);
                        final ArrayList<MultipleItemEntity> list = converter.convert();
                    }
                })
                .build()
                .get();

    }

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mRefreshLayout.setProgressViewOffset(true, 120, 300); //ture:由小变大,120:起始高度；300：终止高度
    }

    //  Recycler布局
    private void initRecyclerView(){
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 5));
        final EcBottomDelegate ecBottomDelegate = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mRefreshHandler.firstPage("index");
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }


//    @Override
//    public void onFocusChange(View v, boolean hasFocus) {
//
//    }

    //搜索用
//    @Override
//    public void onFocusChange(View v, boolean hasFocus) {
//        if (hasFocus) {
//            getParentDelegate().getSupportDelegate().start(new SearchDelegate());
//        }
//    }

}

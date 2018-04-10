package com.bnrc.fireflies.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.bnrc.fireflies.R;
import com.bnrc.fireflies.app.Fireflies;
import com.bnrc.fireflies.delegates.FirefliesDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by apple on 2018/4/7.
 */


//仅仅作为一个容器
public abstract class ProxyActivity extends SupportActivity {

    public abstract FirefliesDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState){
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);

        setContentView(container); //activity中常见的加载视图的方法

        if(savedInstanceState==null){
            loadRootFragment(R.id.delegate_container,setRootDelegate()); //传入根delegate
        }
    }

    //单activity，退出即退出程序
    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}


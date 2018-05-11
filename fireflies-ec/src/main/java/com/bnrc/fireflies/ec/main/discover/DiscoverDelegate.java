package com.bnrc.fireflies.ec.main.discover;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.bnrc.fireflies.delegates.FirefliesDelegate;
import com.bnrc.fireflies.delegates.bottom.BottomItemDelegate;
import com.bnrc.fireflies.delegates.web.WebDelegateImpl;
import com.bnrc.fireflies.ec.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by apple on 2018/4/16.
 */

public class DiscoverDelegate extends BottomItemDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }



    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
    }

//    @Override
//    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
//        super.onLazyInitView(savedInstanceState);
//        final WebDelegateImpl delegate = WebDelegateImpl.create("http://http://58.87.87.220/second.html");
//        delegate.setTopDelegate(this.getParentDelegate());
//        getSupportDelegate().loadRootFragment(R.id.web_discovery_container, delegate);
//    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}

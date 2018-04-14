package com.bnrc.fireflies.ec.launcher;

import android.accounts.AccountManager;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bnrc.fireflies.app.Fireflies;
import com.bnrc.fireflies.app.IUserChecker;
import com.bnrc.fireflies.delegates.FirefliesDelegate;
import com.bnrc.fireflies.ec.R;
import com.bnrc.fireflies.ui.launcher.ILauncherListener;
import com.bnrc.fireflies.ui.launcher.LauncherHolderCreator;
import com.bnrc.fireflies.ui.launcher.OnLauncherFinishTag;
import com.bnrc.fireflies.ui.launcher.ScrollLauncherTag;
import com.bnrc.fireflies.util.storage.FirefliesPreference;

import java.util.ArrayList;

/**
 * Created by apple on 2018/4/11.
 */

public class LauncherScrollDelegate extends FirefliesDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();
    private ILauncherListener mILauncherListener = null;

    private void initBanner() {
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);
        mConvenientBanner
                .setPages(new LauncherHolderCreator(), INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        {
            //如果点击的是最后一个
            if (position == INTEGERS.size() - 1) {
                FirefliesPreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
                //检查用户是否登录了APP
                com.bnrc.fireflies.app.AccountManager.checkAccount(new IUserChecker() {
                    @Override
                    public void onSignIn() {
                        if (mILauncherListener != null) {
                            mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                        }
                    }

                    @Override
                    public void onNotSignIn() {
                        if (mILauncherListener != null) {
                            mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                        }
                    }
                });
            }
        }

    }
}

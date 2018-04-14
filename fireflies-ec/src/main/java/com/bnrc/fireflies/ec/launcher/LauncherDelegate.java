package com.bnrc.fireflies.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.TextView;

import com.bnrc.fireflies.app.AccountManager;
import com.bnrc.fireflies.app.IUserChecker;
import com.bnrc.fireflies.delegates.FirefliesDelegate;
import com.bnrc.fireflies.ec.R;
import com.bnrc.fireflies.ec.R2;
import com.bnrc.fireflies.ui.launcher.ILauncherListener;
import com.bnrc.fireflies.ui.launcher.OnLauncherFinishTag;
import com.bnrc.fireflies.ui.launcher.ScrollLauncherTag;
import com.bnrc.fireflies.util.storage.FirefliesPreference;
import com.bnrc.fireflies.util.timer.BaseTimerTask;
import com.bnrc.fireflies.util.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by apple on 2018/4/10.
 */

public class LauncherDelegate extends FirefliesDelegate implements ITimerListener{
    @BindView(R2.id.tv_launcher_timer)
    TextView mTvTimer = null;

    private Timer mTimer = null;
    private int mCount = 5;
    private ILauncherListener mILauncherListener = null;

    @OnClick(R2.id.tv_launcher_timer) //点击提前结束倒计时
    void onClickTimerView(){
        if(mTimer!=null){
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    private void initTimer(){
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task,0,1000); //0：马上开始倒计时，1000：每隔一秒处理一次
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        if(activity instanceof ILauncherListener){
            mILauncherListener =(ILauncherListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        initTimer();
    }

    //判断是否显示滑动启动页
    private void checkIsShowScroll() {
        if (!FirefliesPreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            start(new LauncherScrollDelegate(), SINGLETASK);
        } else {
            //检查用户是否登录了APP
            AccountManager.checkAccount(new IUserChecker() {
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

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mTvTimer!=null){
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s",mCount));
                    mCount--;
                    if(mCount<0){
                        if(mTimer!=null){
                            mTimer.cancel();;
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }
}

package com.bnrc.fastec;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.bnrc.fireflies.activities.ProxyActivity;
import com.bnrc.fireflies.app.Fireflies;
import com.bnrc.fireflies.delegates.FirefliesDelegate;
import com.bnrc.fireflies.ec.launcher.LauncherDelegate;
import com.bnrc.fireflies.ec.main.EcBottomDelegate;
import com.bnrc.fireflies.ec.sign.ISignListener;
import com.bnrc.fireflies.ec.sign.SignInDelegate;
import com.bnrc.fireflies.ui.launcher.ILauncherListener;
import com.bnrc.fireflies.ui.launcher.OnLauncherFinishTag;

import qiu.niorgai.StatusBarCompat;

public class ExampleActivity extends ProxyActivity implements
        ISignListener,
        ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Fireflies.getConfigurator().withActivity(this);
        StatusBarCompat.translucentStatusBar(this, true);

    }

    @Override
    protected void onPause() {
        super.onPause();
        //JPushInterface.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //JPushInterface.onResume(this);
    }

    @Override
    public FirefliesDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
//                Toast.makeText(this, "启动结束，用户登录了", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
//                Toast.makeText(this, "启动结束，用户没登录", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }

    @Override
    public void post(Runnable runnable) {

    }
}

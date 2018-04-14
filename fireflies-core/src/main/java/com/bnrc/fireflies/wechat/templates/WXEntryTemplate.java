package com.bnrc.fireflies.wechat.templates;

import com.bnrc.fireflies.wechat.BaseWXActivity;
import com.bnrc.fireflies.wechat.BaseWXEntryActivity;
import com.bnrc.fireflies.wechat.FirefliesWechat;

/**
 * Created by apple on 2018/4/12.
 */

public class WXEntryTemplate extends BaseWXEntryActivity {
    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        FirefliesWechat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}

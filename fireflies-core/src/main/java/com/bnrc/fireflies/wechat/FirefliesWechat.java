package com.bnrc.fireflies.wechat;

import android.app.Activity;

import com.bnrc.fireflies.app.ConfigType;
import com.bnrc.fireflies.app.Fireflies;
import com.bnrc.fireflies.wechat.callbacks.IWeChatSignInCallback;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by apple on 2018/4/12.
 */

public class FirefliesWechat {
    public static final String APP_ID = Fireflies.getConfiguration(ConfigType.WE_CHAT_APP_ID);
    public static final String APP_SECRET = Fireflies.getConfiguration(ConfigType.WE_CHAT_APP_SECRET);
    private final IWXAPI WXAPI;
    private IWeChatSignInCallback mSignInCallback = null;

    private static final class Holder {
        private static final FirefliesWechat INSTANCE = new FirefliesWechat();
    }

    public static FirefliesWechat getInstance() {
        return Holder.INSTANCE;
    }

    private FirefliesWechat() {
        final Activity activity = Fireflies.getConfiguration(ConfigType.ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        WXAPI.registerApp(APP_ID);
    }

    public final IWXAPI getWXAPI() {
        return WXAPI;
    }

    public FirefliesWechat onSignSuccess(IWeChatSignInCallback callback) {
        this.mSignInCallback = callback;
        return this;
    }

    public IWeChatSignInCallback getSignInCallback() {
        return mSignInCallback;
    }

    public final void signIn() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }
}

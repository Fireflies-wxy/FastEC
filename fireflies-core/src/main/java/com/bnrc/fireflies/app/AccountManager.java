package com.bnrc.fireflies.app;

import com.bnrc.fireflies.util.storage.FirefliesPreference;

/**
 * Created by apple on 2018/4/11.
 */

public class AccountManager {

    private enum SignTag {
        SIGN_TAG
    }

    //保存用户登录状态，登录后调用
    public static void setSignState(boolean state) {
        FirefliesPreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    private static boolean isSignIn() {
        return FirefliesPreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }


}

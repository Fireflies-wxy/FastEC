package com.bnrc.fireflies.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.bnrc.fireflies.R;
import com.bnrc.fireflies.app.Fireflies;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by apple on 2018/4/7.
 */

public abstract class FirefliesDelegate extends PermissionCheckerDelegate {
    @SuppressWarnings("unchecked")
    public <T extends FirefliesDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}

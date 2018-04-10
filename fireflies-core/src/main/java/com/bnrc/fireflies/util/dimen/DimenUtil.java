package com.bnrc.fireflies.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.bnrc.fireflies.app.Fireflies;

/**
 * Created by apple on 2018/4/10.
 * 得到屏幕的宽高
 */

public final class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Fireflies.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Fireflies.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}

package com.bnrc.fireflies.util.callback;

import android.support.annotation.Nullable;

/**
 * Created by apple on 2018/4/30.
 */

public interface IGlobalCallback<T> {

    void executeCallback(@Nullable T args);

}

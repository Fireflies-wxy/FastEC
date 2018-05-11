package com.bnrc.fireflies.ui.camera;

import android.net.Uri;

/**
 * Created by apple on 2018/4/30.
 */

public class CameraImageBean {

    private Uri mPath = null;

    private static final CameraImageBean INSTANCE = new CameraImageBean();

    public static CameraImageBean getInstance(){
        return INSTANCE;
    }

    public Uri getPath() {
        return mPath;
    }

    public void setPath(Uri mPath) {
        this.mPath = mPath;
    }

}

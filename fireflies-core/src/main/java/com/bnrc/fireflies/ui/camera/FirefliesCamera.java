package com.bnrc.fireflies.ui.camera;

import android.net.Uri;

import com.bnrc.fireflies.delegates.PermissionCheckerDelegate;
import com.bnrc.fireflies.util.file.FileUtil;

/**
 * Created by apple on 2018/4/30.
 * 照相机调用类
 */

public class FirefliesCamera {
    public static Uri createCropFile() {
        return Uri.parse
                (FileUtil.createFile("crop_image",
                        FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }
}

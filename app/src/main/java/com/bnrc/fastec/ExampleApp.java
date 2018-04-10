package com.bnrc.fastec;

import android.app.Application;

import com.bnrc.fireflies.app.Fireflies;
import com.bnrc.fireflies.ec.icon.FontEcModule;
import com.bnrc.fireflies.net.Interceptors.DebugInterceptor;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by apple on 2018/4/7.
 */

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fireflies.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withLoaderDelayed(1000)
                .withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .configure();
    }
}

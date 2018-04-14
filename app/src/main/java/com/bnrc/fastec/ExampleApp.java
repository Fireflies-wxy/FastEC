package com.bnrc.fastec;

import android.app.Application;

import com.bnrc.fastec.R;
import com.bnrc.fireflies.app.Fireflies;
import com.bnrc.fireflies.ec.database.DatabaseManager;
import com.bnrc.fireflies.ec.icon.FontEcModule;
import com.bnrc.fireflies.net.Interceptors.DebugInterceptor;
import com.facebook.stetho.Stetho;
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
                .withApiHost("http://58.87.87.220/")
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .withWeChatAppId("")
                .withWeChatAppSecret("")
                .configure();
        initStetho();
        DatabaseManager.getInstance().init(this);
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}

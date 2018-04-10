package com.bnrc.fireflies.app;

import android.content.Context;
import android.os.Handler;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * Created by apple on 2018/4/6.
 */

public final class Fireflies {

//    public static Configurator init(Context context){
//        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
//        return Configurator.getInstance();
//    }
//
//    public static HashMap<Object, Object> getConfigurations(){
//        return Configurator.getInstance().getFirefliesConfigs();
//    }
//
//    public static Context getApplication(){
//        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
//    }


    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getFirefliesConfigs()
                .put(ConfigType.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplicationContext() {
        return getConfiguration(ConfigType.APPLICATION_CONTEXT);
    }

    public static Handler getHandler() {
        return getConfiguration(ConfigType.HANDLER);
    }

    public static void test(){
    }

}

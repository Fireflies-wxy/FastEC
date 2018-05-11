package com.bnrc.fireflies.app;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.bnrc.fireflies.delegates.web.event.Event;
import com.bnrc.fireflies.delegates.web.event.EventManager;
import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;
import com.blankj.utilcode.util.Utils;
import okhttp3.Interceptor;

/**
 * Created by apple on 2018/4/6.
 * 使用单例模式
 */

public class Configurator {

    private static final HashMap<Object, Object> FIREFLIES_CONFIGS = new HashMap<>();
    private static final Handler HANDLER = new Handler();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    private Configurator(){
        FIREFLIES_CONFIGS.put(ConfigType.CONFIG_READY.name(),false); //配置开始，但未完成
            FIREFLIES_CONFIGS.put(ConfigType.HANDLER, HANDLER);
    }

    //静态内部类
    private static class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }


    final HashMap<Object,Object> getFirefliesConfigs(){
        return FIREFLIES_CONFIGS;
    }


    public final void configure(){
        initIcons();
        Logger.addLogAdapter(new AndroidLogAdapter());
        FIREFLIES_CONFIGS.put(ConfigType.CONFIG_READY,true);
        Utils.init(Fireflies.getApplicationContext());
    }

    public final Configurator withApiHost(String host){
        FIREFLIES_CONFIGS.put(ConfigType.API_HOST,host);
        return this;
    }

    public final Configurator withLoaderDelayed(long delayed) {
        FIREFLIES_CONFIGS.put(ConfigType.LOADER_DELAYED, delayed);
        return this;
    }

    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        FIREFLIES_CONFIGS.put(ConfigType.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        FIREFLIES_CONFIGS.put(ConfigType.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withWeChatAppId(String appId) {
            FIREFLIES_CONFIGS.put(ConfigType.WE_CHAT_APP_ID, appId);
        return this;
    }

    public final Configurator withWeChatAppSecret(String appSecret) {
            FIREFLIES_CONFIGS.put(ConfigType.WE_CHAT_APP_SECRET, appSecret);
        return this;
    }

    public final Configurator withActivity(Activity activity) {
        FIREFLIES_CONFIGS.put(ConfigType.ACTIVITY, activity);
        return this;
    }

    public Configurator withJavascriptInterface(@NonNull String name) {
        FIREFLIES_CONFIGS.put(ConfigType.JAVASCRIPT_INTERFACE, name);
        return this;
    }

    public Configurator withWebEvent(@NonNull String name, @NonNull Event event) {
        final EventManager manager = EventManager.getInstance();
        manager.addEvent(name, event);
        return this;
    }


    //获取配置时调用，保证配置完整性
    private void checkConfiguration(){
        final boolean isReady = (boolean) FIREFLIES_CONFIGS.get(ConfigType.CONFIG_READY);
        if(!isReady){
            throw new RuntimeException("Configuration is not ready, call configure.");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = FIREFLIES_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) FIREFLIES_CONFIGS.get(key);
    }

}

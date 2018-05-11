package com.bnrc.fireflies.delegates.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.bnrc.fireflies.app.Fireflies;
import com.bnrc.fireflies.delegates.web.event.Event;
import com.bnrc.fireflies.delegates.web.event.EventManager;
import com.bnrc.fireflies.util.log.FirefliesLogger;

/**
 * Created by apple on 2018/4/16.
 */

public class FirefliesWebInterface {
    private final WebDelegate DELEGATE;

    private FirefliesWebInterface(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    static FirefliesWebInterface create(WebDelegate delegate) {
        return new FirefliesWebInterface(delegate);
    }

    @SuppressWarnings("unused")
    @JavascriptInterface
    public String event(String params) {
        final String action = JSON.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        FirefliesLogger.d("WEB_EVENT",params);
        if (event != null) {
            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());
            return event.execute(params);
        }
        return null;
    }
}

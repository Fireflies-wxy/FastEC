package com.bnrc.fireflies.delegates.web.event;

import com.bnrc.fireflies.util.log.FirefliesLogger;

/**
 * Created by apple on 2018/4/16.
 */

public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        FirefliesLogger.e("UndefineEvent", params);
        return null;
    }
}

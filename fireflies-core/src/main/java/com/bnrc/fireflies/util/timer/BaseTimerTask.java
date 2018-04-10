package com.bnrc.fireflies.util.timer;

import java.util.TimerTask;

/**
 * Created by apple on 2018/4/10.
 */

public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener;

    public BaseTimerTask(ITimerListener timerListener){
        this.mITimerListener = timerListener;
    }

    @Override
    public void run() {
        if(mITimerListener != null){
            mITimerListener.onTimer();
        }
    }
}

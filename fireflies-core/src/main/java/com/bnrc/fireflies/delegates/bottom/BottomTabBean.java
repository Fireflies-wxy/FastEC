package com.bnrc.fireflies.delegates.bottom;

/**
 * Created by apple on 2018/4/12.
 */

public final class BottomTabBean {
    private final CharSequence ICON;
    private final CharSequence TITLE;

    public BottomTabBean(CharSequence icon, CharSequence title){
        this.ICON = icon;
        this.TITLE = title;
    }

    public CharSequence getIcon(){
        return ICON;
    }

    public CharSequence getTitle(){
        return TITLE;
    }

}

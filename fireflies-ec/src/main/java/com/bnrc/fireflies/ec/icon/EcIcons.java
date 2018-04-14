package com.bnrc.fireflies.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by apple on 2018/4/8.
 */

public enum  EcIcons implements Icon{
    icon_scan('\ue602'),  //扫码字体
    icon_ali_pay('\ue606');  //支付字体

    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}

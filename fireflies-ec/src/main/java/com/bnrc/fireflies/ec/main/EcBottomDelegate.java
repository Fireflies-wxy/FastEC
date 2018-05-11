package com.bnrc.fireflies.ec.main;

import android.graphics.Color;
import android.util.Log;

import com.bnrc.fireflies.delegates.bottom.BaseBottomDelegate;
import com.bnrc.fireflies.delegates.bottom.BottomItemDelegate;
import com.bnrc.fireflies.delegates.bottom.BottomTabBean;
import com.bnrc.fireflies.delegates.bottom.ItemBuilder;
import com.bnrc.fireflies.delegates.web.WebDelegate;
import com.bnrc.fireflies.ec.main.cart.ShopCartDelegate;
import com.bnrc.fireflies.ec.main.discover.DiscoverDelegate;
import com.bnrc.fireflies.ec.main.index.IndexDelegate;
import com.bnrc.fireflies.ec.main.persional.PersonalDelegate;
import com.bnrc.fireflies.ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * Created by apple on 2018/4/12.
 */

public class EcBottomDelegate extends BaseBottomDelegate {
    private static final String TAG = "EcBottomDelegate";
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        Log.d(TAG,"EcBottomDelegate");
        final LinkedHashMap<BottomTabBean,BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new PersonalDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}

package com.bnrc.fireflies.ec.main.sort.content;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2018/4/15.
 */

public class SectionDataConverter{

    private static final String TAG = "SectionDataConverter";

    final List<SectionBean> convert(String json) {
        final List<SectionBean> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(json).getJSONArray("data");


        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            Log.d(TAG+" roundi"+i+" id: ", String.valueOf(id));
            final String title = data.getString("section");
            Log.d(TAG+" roundi"+i+" title: ",title);

            //添加title
            final SectionBean sectionTitleBean = new SectionBean(true, title);
            sectionTitleBean.setId(id);
            sectionTitleBean.setIsMore(true);
            dataList.add(sectionTitleBean);

            final JSONArray goods = data.getJSONArray("goods");
            //商品内容循环
            final int goodSize = goods.size();
            for (int j = 0; j < goodSize; j++) {
                final JSONObject contentItem = goods.getJSONObject(j);
                final int goodsId = contentItem.getInteger("goods_id");
                Log.d(TAG+" roundj"+j+" goodsId: ", String.valueOf(goodsId));
                final String goodsName = contentItem.getString("goods_name");
                Log.d(TAG+" roundj"+j+" goodsname: ", goodsName);
                final String goodsThumb = contentItem.getString("goods_thumb");
                Log.d(TAG+" roundj"+j+" goodsThumb: ", goodsThumb);
                //获取内容
                final SectionContentItemEntity itemEntity = new SectionContentItemEntity();
                itemEntity.setGoodsId(goodsId);
                itemEntity.setGoodsName(goodsName);
                itemEntity.setGoodsThumb(goodsThumb);
                //添加内容
                dataList.add(new SectionBean(itemEntity));
            }
            //商品内容循环结束
        }
        //Section循环结束

        return dataList;
    }
}

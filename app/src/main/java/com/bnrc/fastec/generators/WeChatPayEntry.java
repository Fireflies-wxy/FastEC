package com.bnrc.fastec.generators;

import com.bnrc.fireflies.wechat.templates.WXPayEntryTemplate;
import com.bnrc.fireflies_annotations.PayEntryGenerator;

/**
 * Created by apple on 2018/4/12.
 */

@SuppressWarnings("unused")
@PayEntryGenerator(
        packageName = "com.bnrc.fastec.example",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}

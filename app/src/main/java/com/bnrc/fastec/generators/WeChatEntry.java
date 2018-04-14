package com.bnrc.fastec.generators;

import com.bnrc.fireflies.wechat.templates.WXEntryTemplate;
import com.bnrc.fireflies_annotations.EntryGenerator;

/**
 * Created by apple on 2018/4/12.
 */


@SuppressWarnings("unused")
@EntryGenerator(
        packageName = "com.bnrc.fastec.example",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}

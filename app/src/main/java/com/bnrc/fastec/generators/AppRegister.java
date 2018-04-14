package com.bnrc.fastec.generators;

import com.bnrc.fireflies.wechat.templates.AppRegisterTemplate;
import com.bnrc.fireflies_annotations.AppRegisterGenerator;

/**
 * Created by apple on 2018/4/12.
 */

@SuppressWarnings("unused")
@AppRegisterGenerator(
        packageName = "com.bnrc.fastec.example",
        registerTemplate = AppRegisterTemplate.class
)
public class AppRegister {
}

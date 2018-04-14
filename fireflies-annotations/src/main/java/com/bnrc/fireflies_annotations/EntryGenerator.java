package com.bnrc.fireflies_annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by apple on 2018/4/12.
 */

@Target(ElementType.TYPE) //用在类上
@Retention(RetentionPolicy.SOURCE)
public @interface EntryGenerator {
    String packageName();
    Class<?> entryTemplate();
}

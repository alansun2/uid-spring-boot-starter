package com.alan344.uid.baidu;

import com.alan344.uid.baidu.impl.DefaultUidGenerator;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author AlanSun
 * @date 2022/10/27 14:04
 * <p>
 * id 生成器
 **/
@Target({FIELD})
@Retention(RUNTIME)
public @interface UidGen {

    Class<? extends UidGenerator> strategy() default DefaultUidGenerator.class;

    /**
     * 对应的字段名称
     *
     * @return 字段名称
     */
    String columnName() default "";

    /**
     * 如果 columnName 为空, 此值为 true 则把属性名从驼峰格式改为下划线格式
     *
     * @return true: 把属性值从驼峰转为下划线格式
     */
    boolean columnNameFromProperty() default true;
}
package com.spdb.annotation;

import com.spdb.enums.LogEnum;

import java.lang.annotation.*;

/**
 * @author CB
 * @Title: ArchivesLog
 * @Package
 * @Description: <日志打印注解类>
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ArchivesLog {

    /** 要执行的操作类型比如：add操作 **/
    public String operationType() default "";

    /** 要执行的具体操作比如：添加用户 **/
    public String operationName() default "";

    LogEnum[] LogEnum() default {};
}
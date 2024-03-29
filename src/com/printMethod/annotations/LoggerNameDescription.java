package com.printMethod.annotations;

import java.lang.annotation.*;

/**
 * @Auther: liteng
 * @Date: 2018/7/23 10:48
 * @Description: 注解中写方法的名字，答应日志时直接获取这个名字当做日志
 */


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoggerNameDescription {
    String methodNameDescription() default "没有被注解定义的方法";
}

package com.mqm.automation.ui.services.execution;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
public @interface UnstableTest {
    String since() default "";
    String details() default "";
}
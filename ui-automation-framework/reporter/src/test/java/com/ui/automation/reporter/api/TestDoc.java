package com.ui.automation.reporter.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
/**
 * Created with IntelliJ IDEA.
 * User: coheney
 * Date: 07/01/14
 * Time: 11:29
 * To change this template use File | Settings | File Templates.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value={CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, PARAMETER, TYPE})
public @interface TestDoc {
    String name();
    String description() default "";
}

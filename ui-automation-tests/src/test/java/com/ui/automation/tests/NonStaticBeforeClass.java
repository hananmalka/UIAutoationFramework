package com.ui.automation.tests;

import java.lang.annotation.*;

/**
 * Created by Dana Shalev on 22/11/2015.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Inherited
public @interface NonStaticBeforeClass {
}
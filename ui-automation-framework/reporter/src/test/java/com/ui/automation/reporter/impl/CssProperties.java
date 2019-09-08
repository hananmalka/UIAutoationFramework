package com.ui.automation.reporter.impl;

/**
 * Created with IntelliJ IDEA.
 * User: coheney
 * Date: 10/03/14
 * Time: 11:34
 * To change this template use File | Settings | File Templates.
 */
public enum CssProperties {
    IMG_IGNORE("ignore-img"),
    IMG_ASS_FAIL("assfail-img"),
    IMG_UNSTABLE_FAIL("assfail-fail-img"),
    IMG_UNSTABLE_SUCCEED("assfail-succeed-img"),
    IMG_SUCCESS("success-img"),
    IMG_EXPAND("expand-img"),
    IMG_COLLAPSE("collapse-img"),
    IMG_CHROME("chrome-img"),
    IMG_IE("ie-img"),
    IMG_HELP("help-img"),
    IMG_STEP_TEST("test-step-img step"),
    IMG_STEP_FUNCTIONAL("functional-step-img step"),
    IMG_STEP_ERROR("error-step-img step"),
    IMG_STEP_INFO("info-step-img step"),
    IMG_STEP_WARN("warning-step-img step"),
    IMG_STEP_DEBUG("debug-step-img step"),
    STEP_TEST("test-step"),
    STEP_FUNCTIONAL("functional-step"),
    STEP_ERROR("error-step"),
    STEP_INFO("info-step"),
    STEP_WARN("warning-step"),
    STEP_DEBUG("debug-step"),
    CHECKBOX("checkbox-"),
    IMG_FAIL("fail-img"),
    HOUR_FORMAT("HH:mm:ss"),
    ELLIPSIS_CLASS("ellipsis"),
    BEFORE_CLASS_TXT("Before Class"),
    AFTER_CLASS_TXT("After Class"),
    // TODO: enable configuring
    TITLE("Aura - Blue - CMS UI Automation"),
    COLOR_RED_CLASS("failed-test"),
    HAND_CURSOR("hover-cursor-hand");

    private final String _value;
    private  Integer checkboxCounter = 0;
    CssProperties(String value) {
        this._value = value;
    }
    public String checkBoxCounter(){
        return this._value + String.valueOf(checkboxCounter++);
    }

    public String value() {
        return this._value;
    }



}





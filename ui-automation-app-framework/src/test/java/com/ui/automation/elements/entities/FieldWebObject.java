package com.ui.automation.elements.entities;

import com.ui.automation.elements.controls.IControl;

public class FieldWebObject {

    public IControl webElement;
    public Class clazz;

    FieldWebObject(IControl webElement, Class clazz) {
        this.webElement = webElement;
        this.clazz = clazz;
    }
}

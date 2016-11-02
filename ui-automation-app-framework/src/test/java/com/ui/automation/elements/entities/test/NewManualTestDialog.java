package com.ui.automation.elements.entities.test;

import com.ui.automation.elements.dialogs.NewEntityDialog;

/**
 * Created by Dana Shalev on 30/11/2015.
 */
public class NewManualTestDialog extends NewEntityDialog {

    public NewManualTestDialog setName(String value) {
        textbox("name").setValue(value);
        return this;
    }
}

package com.ui.automation.elements.entities.appmodule;

import com.ui.automation.elements.dialogs.NewEntityDialog;

/**
 * Created by Dana Shalev on 25/11/2015.
 */
public class NewAppModuleDialog extends NewEntityDialog {

    public NewEntityDialog setName(String value) {
        textbox("name").setValue(value);
        return this;
    }
}

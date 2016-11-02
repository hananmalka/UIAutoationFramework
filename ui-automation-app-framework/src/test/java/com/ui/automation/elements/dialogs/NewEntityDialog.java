package com.ui.automation.elements.dialogs;

import com.ui.automation.locator.Locator;
import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseTopLevelElement;
import com.ui.automation.elements.controls.Button;
import com.ui.automation.elements.controls.TextBox;

/**
 * Created by Dana Shalev on 25/11/2015.
 */
public class NewEntityDialog extends BaseTopLevelElement {

    public NewEntityDialog() {
        // TODO: is this the right locator
        super(Locator.className("alm-entity-form-entity-dialog"));
    }

    public NewEntityDialog clickAdd(boolean close) {
        return clickButton(close ? "add" : "add-another");
    }

    public NewEntityDialog clear() {
        return clickButton("restore");
    }

    public NewEntityDialog cancel() {
        return clickButton("cancel");
    }

    protected NewEntityDialog clickButton(String command) {
        Element button = button(command);
        getDriver().expects().elementToBeEnabled(button);
        getDriver().actions().click(button);
        return this;
    }

    protected TextBox textbox(String dataAid) {
        return new TextBox(Locator.xpath(".//input[@type='text' and ancestor::div[@data-aid='" + dataAid + "']]"), this);
    }

    protected Button button(String command) {
        return new Button(Locator.dataAid("alm-entity-form-buttons-" + command), this);
    }
}

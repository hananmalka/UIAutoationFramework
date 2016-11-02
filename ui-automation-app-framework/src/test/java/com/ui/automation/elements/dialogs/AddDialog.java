package com.ui.automation.elements.dialogs;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.base.BaseTopLevelElement;
import com.ui.automation.elements.controls.Button;
import com.ui.automation.elements.controls.TextBox;
import com.ui.automation.locator.Locator;

/**
 * Created by azariam on 18/04/2016.
 */

// this extends BaseTopLevelElement because the dialog web element is under <body>
public class AddDialog extends BaseTopLevelElement {

    private Button xButton;
    private Button okButton;
    private Button cancelButton;
    private BaseElement title;
    private BaseElement errorMessage;
    private TextBox inputTextBox;

    public AddDialog(Locator selfLocator, Locator title, Locator xBtn, Locator okBtn, Locator cancelBtn, Locator errorMessage) {
        super(selfLocator);
        this.title = new BaseElement(title, this);
        this.errorMessage = new BaseElement(errorMessage, this);
        this.xButton = new Button(xBtn, this);
        this.okButton = new Button(okBtn, this);
        this.cancelButton = new Button(cancelBtn, this);
        this.inputTextBox = new TextBox(this);
    }

    public Button getXButton() {
        return xButton;
    }
    public Button getOKButton() {
        return okButton;
    }
    public Button getCancelButton() {
        return cancelButton;
    }
    public BaseElement getTitle() {
        return title;
    }
    public BaseElement getErrorMessage() {
        return errorMessage;
    }
    public TextBox getInputTextBox() {
        return inputTextBox;
    }

    public void expectErrorMessageToBe(String expectedText) {
        getDriver().expects().elementToContainAttrValue(errorMessage, "data-error-text", expectedText);
    }

}

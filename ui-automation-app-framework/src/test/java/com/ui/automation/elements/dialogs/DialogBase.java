package com.ui.automation.elements.dialogs;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.base.BaseTopLevelElement;
import com.ui.automation.elements.controls.Button;
import com.ui.automation.locator.Locator;

/**
 * Created by azariam on 26/05/2016.
 */
public class DialogBase extends BaseTopLevelElement {

    private Button xButton;
    private Button approveButton;
    private Button cancelButton;
    private BaseElement title;

    public DialogBase(Locator selfLocator, Locator okBtn) {
        super(selfLocator);
        this.approveButton = new Button(okBtn, this);
    }

    public DialogBase(Locator selfLocator, Locator okBtn, Locator xBtn) {
        super(selfLocator);
        this.approveButton = new Button(okBtn, this);
        this.xButton = new Button(xBtn, this);
    }

    public DialogBase(Locator selfLocator, Locator title, Locator okBtn, Locator xBtn) {
        super(selfLocator);
        this.approveButton = new Button(okBtn, this);
        this.xButton = new Button(xBtn, this);
    }

    public DialogBase(Locator selfLocator, Locator title, Locator xBtn, Locator okBtn, Locator cancelBtn) {
        super(selfLocator);
        this.title = new BaseElement(title, this);
        this.xButton = new Button(xBtn, this);
        this.approveButton = new Button(okBtn, this);
        this.cancelButton = new Button(cancelBtn, this);
    }

    public Button getXButton() {
        return xButton;
    }
    public Button getApproveButton() {
        return approveButton;
    }
    public Button getCancelButton() {
        return cancelButton;
    }
    public BaseElement getTitle() {
        return title;
    }

}

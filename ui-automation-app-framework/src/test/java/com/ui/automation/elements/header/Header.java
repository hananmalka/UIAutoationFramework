package com.ui.automation.elements.header;

import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.controls.Button;
import com.ui.automation.locator.Locator;

/**
 * Created by Dana Shalev on 18/11/2015.
 */
public class Header extends BaseElement {

    private Button helpButton;
    private Button navigationButton;
    private Button accountButton;

    public Header(Locator locator, Element parent) {
        super(locator, parent);
    }

    public Header(Locator locator, Element parent, Locator helpBtn, Locator navigationBtn) {
        super(locator, parent);
        this.helpButton = new Button(helpBtn, this);
        this.navigationButton = new Button(navigationBtn, this);
    }

    public Header(Locator locator, Element parent, Locator helpBtn, Locator navigationBtn, Locator accountBtn) {
        super(locator, parent);
        this.helpButton = new Button(helpBtn, this);
        this.navigationButton = new Button(navigationBtn, this);
        this.accountButton = new Button(accountBtn, this);
    }

    //TODO: change navigationMenu accordingly.
    public NavigationMenu navigationMenu() {
        return new NavigationMenu(Locator.dataAid("main-menu"), this);
    }
}

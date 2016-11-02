package com.ui.automation.elements.shadow;

import com.ui.automation.elements.base.BaseTopLevelElement;
import com.ui.automation.locator.Locator;

/**
 * Created by azariam on 09/06/2016.
 */
public class ShadowRoot extends BaseTopLevelElement {

    public ShadowRoot(Locator locator) {
        super(locator);
    }

    public void executeJS(String jsScript){
        getDriver().actions().executeJavaScript(jsScript);
    }

}

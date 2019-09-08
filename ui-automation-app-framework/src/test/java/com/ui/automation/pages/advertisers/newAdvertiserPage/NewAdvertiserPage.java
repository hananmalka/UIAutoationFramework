package com.ui.automation.pages.advertisers.newAdvertiserPage;

import com.ui.automation.elements.base.BaseTopLevelElement;
import com.ui.automation.locator.Locator;
import com.ui.automation.pages.advertisers.newAdvertiserPage.elements.NewAdvertiserPageHeader;
import com.ui.automation.pages.advertisers.newAdvertiserPage.elements.panesContainers.NewAdvertiserForm;

public class NewAdvertiserPage extends BaseTopLevelElement {

    public NewAdvertiserPageHeader newAdvertiserPageHeader;
    public NewAdvertiserForm newAdvertiserForm;


    public NewAdvertiserPage() {
        super(Locator.className("AdvertiserContainer"));
        newAdvertiserPageHeader = new NewAdvertiserPageHeader(this);
        newAdvertiserForm = new NewAdvertiserForm(this);
    }
}

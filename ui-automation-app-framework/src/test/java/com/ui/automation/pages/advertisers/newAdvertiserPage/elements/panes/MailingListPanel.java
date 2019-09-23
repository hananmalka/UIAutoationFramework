package com.ui.automation.pages.advertisers.newAdvertiserPage.elements.panes;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Panel;
import com.ui.automation.elements.controls.AutoCompleteTag;
import com.ui.automation.locator.Locator;

public class MailingListPanel extends Panel {

    public MailingListPanel(BaseElement parent) {
        super(Locator.className("mailing-list-panel"), parent);
    }

    public AutoCompleteTag mailingListField() {
        return autoCompleteTagField();
    }
}

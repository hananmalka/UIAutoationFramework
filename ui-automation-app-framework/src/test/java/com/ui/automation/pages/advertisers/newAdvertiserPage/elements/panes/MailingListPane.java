package com.ui.automation.pages.advertisers.newAdvertiserPage.elements.panes;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Pane;
import com.ui.automation.elements.controls.AutoCompleteTag;
import com.ui.automation.locator.Locator;

public class MailingListPane extends Pane {

    public MailingListPane(BaseElement parent) {
        super(Locator.className("mailing-list-panel"), parent);
    }

    public AutoCompleteTag mailingListField() {
        return autoCompleteTagField();
    }
}

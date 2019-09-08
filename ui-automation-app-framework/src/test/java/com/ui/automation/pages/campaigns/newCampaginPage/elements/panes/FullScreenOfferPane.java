package com.ui.automation.pages.campaigns.newCampaginPage.elements.panes;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Pane;
import com.ui.automation.elements.controls.textbox.MultiLineTextBox;
import com.ui.automation.elements.controls.dropdowns.StaticDropDown;
import com.ui.automation.elements.controls.textbox.TextBox;
import com.ui.automation.enums.TextBoxLabels;
import com.ui.automation.locator.Locator;

public class FullScreenOfferPane extends Pane {

    public FullScreenOfferPane(BaseElement parent) {
        super(Locator.className("fullScreenOfferComponent"), parent);
    }

    public StaticDropDown fsoOfferSettingsDropDown() {
        return new StaticDropDown(this);
    }

    public MultiLineTextBox titleTextBox() {
        String titleFieldLabel = TextBoxLabels.FsoLabels.TITLE.getDisplayableLabel();
        return multiLineTextBox(titleFieldLabel);
    }

    public MultiLineTextBox summaryTextBox() {
        String summaryFieldLabel = TextBoxLabels.FsoLabels.SUMMARY.getDisplayableLabel();
        return multiLineTextBox(summaryFieldLabel);
    }

    public MultiLineTextBox descriptionTextBox() {
        String descriptionFieldLabel = TextBoxLabels.FsoLabels.DESCRIPTION.getDisplayableLabel();
        return multiLineTextBox(descriptionFieldLabel);
    }

    public MultiLineTextBox actionTextBox() {
        String actionTextFieldLabel = TextBoxLabels.FsoLabels.ACTION_TEXT.getDisplayableLabel();
        return multiLineTextBox(actionTextFieldLabel);
    }

    public TextBox textColorTextBox() {
        String textColorFieldLabel = TextBoxLabels.FsoLabels.TEXT_COLOR.getDisplayableLabel();
        return textBoxField(textColorFieldLabel);
    }

    public TextBox textBackgroundTextBox() {
        String textBackgroundFieldLabel = TextBoxLabels.FsoLabels.TEXT_BACKGROUND.getDisplayableLabel();
        return textBoxField(textBackgroundFieldLabel);
    }
}

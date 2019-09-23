package com.ui.automation.elements.containers;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.controls.*;
import com.ui.automation.elements.controls.dropdowns.AuraDropDown;
import com.ui.automation.elements.controls.dropdowns.AutoCompleteDropDown;
import com.ui.automation.elements.controls.dropdowns.DropDown;
import com.ui.automation.elements.controls.dropdowns.StaticDropDown;
import com.ui.automation.elements.controls.textbox.MultiLineTextBox;
import com.ui.automation.elements.controls.textbox.TextBox;
import com.ui.automation.locator.Locator;

/**
 * Created by azariam on 26/05/2016.
 * This class represents panes in html
 * There's no special behavior, this is simply a container to fit the logic of how the page is built.
 */
public abstract class Panel extends BaseElement {

    public Panel(Locator self, BaseElement parent) {
        super(self, parent);
    }

    protected TextBox textBoxField(String label) {
        return new TextBox(label, this);
    }

    protected TextBox textBoxField(Locator locator) {
        return new TextBox(locator, this);
    }

    protected MultiLineTextBox multiLineTextBox(String label) {
        return new MultiLineTextBox(label, this);
    }

    protected DropDown dropDownField(Locator locator) {
        return new DropDown(locator, this);
    }

    protected Button buttonField(Locator locator) {
        return new Button(locator, this);
    }

    protected CheckBox checkBoxField() {
        return new CheckBox(this);
    }

    protected CheckBox checkBoxField(Locator locator) {
        return new CheckBox(locator, this);
    }

    protected AutoCompleteTag autoCompleteTagField() {
        return new AutoCompleteTag(this);
    }

    protected AutoCompleteTag autoCompleteTagField(Locator locator) {
        return new AutoCompleteTag(locator, this);
    }

    protected AutoCompleteDropDown autoCompleteDropDownField() {
        return new AutoCompleteDropDown(this);
    }

    protected StaticDropDown staticDropDown() {
        return new StaticDropDown(this);
    }

    protected ToggleButton toggleButton() {
        return new ToggleButton(this);
    }

    protected ToggleButton toggleButton(Locator locator) {
        return new ToggleButton(locator, this);
    }

    protected AuraDropDown auraDropDownField() {
        return new AuraDropDown(this);
    }

    protected AuraDropDown auraDropDownField(Locator locator) {
        return new AuraDropDown(locator, this);
    }
}

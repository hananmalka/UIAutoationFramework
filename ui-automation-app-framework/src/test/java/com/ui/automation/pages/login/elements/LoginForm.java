package com.ui.automation.pages.login.elements;

import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.controls.Button;
import com.ui.automation.elements.controls.textbox.TextBox;
import com.ui.automation.elements.impl.RootElement;
import com.ui.automation.locator.Locator;

public class LoginForm {

    private final Element parent = RootElement.getInstance();

    public TextBox usernameTextBox(){
        return new TextBox(Locator.id("usernameWarning"), parent);
    }

    public TextBox passwordTextBox(){
        return new TextBox(Locator.id("passwordWarning"), parent);
    }

    public Button loginButton() {
        return new Button(Locator.className("btn-primary"), parent);
    }
}

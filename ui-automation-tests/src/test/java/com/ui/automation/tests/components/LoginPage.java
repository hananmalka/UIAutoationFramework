package com.ui.automation.tests.components;

import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.controls.Button;
import com.ui.automation.elements.controls.TextBox;
import com.ui.automation.elements.impl.RootElement;
import com.ui.automation.locator.Locator;

public class LoginPage {

    private TextBox usernameTextbox;
    private TextBox passwordTextbox;

    private Button loginButton;

    public LoginPage() {
        Element parent = RootElement.getInstance();
        usernameTextbox = new TextBox(Locator.id("username"), parent);
        passwordTextbox = new TextBox(Locator.id("password"), parent);

        loginButton = new Button(Locator.id("login"), parent);
    }

    public void setUsername(String username) {
        usernameTextbox.setValue(username);
    }

    public void setPassword(String password) {
        passwordTextbox.setValue(password);
    }

    public void clickLogin() {
        loginButton.click();
    }
}

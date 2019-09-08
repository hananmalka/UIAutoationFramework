package com.ui.automation.pages.login;

import com.ui.automation.pages.login.elements.LoginForm;

public class LoginPage {

    private LoginForm loginForm;

    public LoginPage() {
        loginForm = new LoginForm();
    }

    private void setUsername(String username) {
        loginForm.usernameTextBox().setValue(username);
    }

    private void setPassword(String password) {
        loginForm.passwordTextBox().setValue(password);
    }

    private void clickLogin() {
        loginForm.loginButton().click();
    }

    public void performLogin(String username, String password) {
        setUsername(username);
        setPassword(password);
        clickLogin();
    }
}

package com.ui.automation.tests.components;

import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.controls.Button;
import com.ui.automation.elements.impl.RootElement;
import com.ui.automation.locator.Locator;
import com.ui.automation.selenium.lifecycle.DriverTestContext;
import com.ui.automation.selenium.wd.Browser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OOMainApp {

    private String baseUrl;

    @Autowired
    private DriverTestContext driverTestContext;

    public void goToFlowLibrary() {
        Browser browser = driverTestContext.getDriver().browser();
        browser.get(baseUrl + "/#/contentWorkspace/flowLibrary");

        PauseUtil.waitUntilPageLoaded();
    }

    public String getBrowserURL() {
        Browser browser = driverTestContext.getDriver().browser();
        return browser.getCurrentURL();
    }

    public void login(String username, String password) {
        LoginPage loginPage = new LoginPage();

        loginPage.setUsername(username);
        loginPage.setPassword(password);

        loginPage.clickLogin();

        PauseUtil.waitUntilPageLoaded();
    }

    public void logout() {
        Element parent = RootElement.getInstance();
        Button logoutButton = new Button(Locator.className("logout-button"), parent);
        logoutButton.click();

        PauseUtil.waitUntilPageLoaded();
    }

    public void openBrowser(String browserType) {
        Browser browser = driverTestContext.getDriver().browser();
        browser.open(browserType);
    }

    public void goToOO(String ooUrl) {
        Browser browser = driverTestContext.getDriver().browser();
        browser.get(ooUrl);

        baseUrl = ooUrl;
    }

    public void goToRunView() {
        Browser browser = driverTestContext.getDriver().browser();
        browser.get(baseUrl + "/#/runtimeWorkspace/runs");

        PauseUtil.waitUntilPageLoaded();
    }
    public void closeBrowser(){
        Browser browser = driverTestContext.getDriver().browser();
        browser.close();
    }

}

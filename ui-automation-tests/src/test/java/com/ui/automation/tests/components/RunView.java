package com.ui.automation.tests.components;


import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.impl.RootElement;
import com.ui.automation.locator.Locator;
import com.ui.automation.selenium.lifecycle.DriverTestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RunView {

    @Autowired
    private DriverTestContext driverTestContext;

    public String getRunStatus(String runID) {
        String xpath = "//*[@id=\"runGrid\"]/div/div/div[div/span=+ " + runID + "]/div[2]/div/span";

        BaseElement elem = new BaseElement(Locator.xpath(xpath), RootElement.getInstance());

        return elem.toString();
    }

    public String getLastRunID() {
        // 1 - first run in grid
        // 3 - run id column
        String xpath = "//*[@id=\"runGrid\"]/div/div[@class=\"grid-canvas\"]/div[1]/div[3]/span";

        BaseElement elem = new BaseElement(Locator.xpath(xpath), RootElement.getInstance());

        return driverTestContext.getDriver().finds().getText(elem);
    }

    public String getRunStatusLastRun() {
        String xpath = "//*[@id=\"runGrid\"]/div/div[@class=\"grid-canvas\"]/div[1]/div[4]/div/span";

        BaseElement elem = new BaseElement(Locator.xpath(xpath), RootElement.getInstance());

        return driverTestContext.getDriver().finds().getText(elem);
    }
}

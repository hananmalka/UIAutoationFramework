package com.ui.automation.selenium.wd.specificElements;

import com.ui.automation.elements.api.Element;
import com.ui.automation.selenium.wd.BaseDriverExecutorImpl;
import com.ui.automation.selenium.wd.DriverServices;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RelatedEntityActionsImpl extends BaseDriverExecutorImpl implements RelatedEntityActions {
    public RelatedEntityActionsImpl(DriverServices driverservices) {
        super(driverservices);
    }

    @Override
    public void selectItem(Element element) {
        By driverServicesBy = driverServices.getBy(element);
        WebDriverWait wait = new WebDriverWait(driverServices.getDriver(), 30000);
        WebElement item = wait.until(ExpectedConditions.visibilityOfElementLocated(driverServicesBy));
        driverServices.executeJavaScript("$(arguments[0]).scope().itemIsHovered = true;$(arguments[0]).scope().$apply();", item);
    }
}

package com.ui.automation.selenium.browser;

import com.ui.automation.common.exception.MaasUIAutomationException;
import com.ui.automation.selenium.lifecycle.DriverTestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link AlertPage}
 */
@Component
public class AlertPageImpl implements AlertPage {

    @Autowired
    private DriverTestContext context;

    public void dismiss() {
        context.getDriver().browser().alert().dismiss();
    }

    public void accept() {
        context.getDriver().browser().alert().accept();
    }

    public void expectToContainText(String text) {
        String alertText = getText();
        if (!alertText.contains(text)) {
             throw new MaasUIAutomationException("AlertPage text '" + alertText + "' does not contain expected '" + text + "'");
        }
    }

    public String getText() {
        return context.getDriver().browser().alert().getText();
    }
}

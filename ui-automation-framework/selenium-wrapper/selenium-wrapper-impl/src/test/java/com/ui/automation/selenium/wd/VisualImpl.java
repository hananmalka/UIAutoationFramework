package com.ui.automation.selenium.wd;

import com.applitools.eyes.Eyes;
import org.openqa.selenium.WebDriver;

/**
 * Implementation of {@link Visual}
 */
public class VisualImpl implements Visual {

    private final Eyes eyes;
    private final WebDriver driver;

    public VisualImpl(Eyes eyes, WebDriver driver) {
        this.eyes = eyes;
        this.driver = driver;
    }

    @Override
    public void checkWindow() {
        eyes.checkWindow();
    }
}

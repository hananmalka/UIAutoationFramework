package com.ui.automation.selenium.wd;

/**
 * Null object
 */
class NoVisuals implements Visual {

    private static NoVisuals instance = null;

    static synchronized NoVisuals getInstance() {
        if (instance == null) {
            instance = new NoVisuals();
        }
        return instance;
    }

    @Override
    public void checkWindow() {
        // Do nothing
    }
}

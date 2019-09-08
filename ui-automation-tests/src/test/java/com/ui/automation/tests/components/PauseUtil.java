package com.ui.automation.tests.components;

public class PauseUtil {
    public static void waitUntilPageLoaded() {
        pause(3);
    }

    public static void pause(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package com.ui.automation.enums;

public enum FsoOfferSettings {

    FULL_SCREEN_BACKGROUND("Full Screen Background"),
    TOP_SCREEN_BANNER("Top Screen Banner");

    String settings;

    FsoOfferSettings(String settings) {
        this.settings = settings;
    }

    public String getValue() {
        return this.settings;
    }
}


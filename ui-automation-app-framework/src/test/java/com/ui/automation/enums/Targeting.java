package com.ui.automation.enums;

public enum Targeting {

    CARRIER("Carrier");

    String targeting;

    Targeting(String targeting) {
        this.targeting = targeting;
    }

    public String getValue() {
        return this.targeting;
    }
}

package com.ui.automation.enums;

public enum LimitationEnum {

    DAILY_BUDGET("Daily Limitations", "Budget", "Daily Budget");

    String group;
    String item;
    String label;

    LimitationEnum(String group, String item, String label) {
        this.group = group;
        this.item = item;
        this.label = label;
    }

    public String getGroup() {
        return this.group;
    }

    public String getItem() {
        return this.item;
    }

    public String getLabel() {
        return this.label;
    }
}

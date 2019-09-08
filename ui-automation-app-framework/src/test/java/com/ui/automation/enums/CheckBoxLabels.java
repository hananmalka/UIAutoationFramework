package com.ui.automation.enums;

public final class CheckBoxLabels {

    public interface CheckBoxLabelsInterface {

        String getDisplayableLabel();
    }

    public static enum DefaultPlacementsLabels implements CheckBoxLabels.CheckBoxLabelsInterface {

        DYNAMIC_PRELOAD("Dynamic Preload"),
        OOBE_PRESELECTED("OOBE Preselected"),
        FULL_SCREEN_OFFER("Full-Screen Offer"),
        OOBE_NON_PRESELECTED("OOBE Non-Preselected"),
        GAMEBOX("Gamebox"),
        ENGAGE_APP_PROMOTION("Engage App Promotion");
        private final String label;

        DefaultPlacementsLabels(final String label) {
            this.label = label;
        }

        public String getDisplayableLabel() {
            return label;
        }
    }

    public static enum EngageNotificationsSettingsLabels implements TextBoxLabels.TextBoxLabelInterface {

        MAIN_TEXT("Main Text"),
        BODY_TEXT("Body Text");

        private final String label;

        EngageNotificationsSettingsLabels(String label) {
            this.label = label;
        }

        public String getDisplayableLabel() {
            return label;
        }
    }

    public CheckBoxLabels() {
        super();
    }
}

package com.ui.automation.enums;

public final class TextBoxLabels {

    public interface TextBoxLabelInterface {

        String getDisplayableLabel();
    }

    public static enum FsoLabels implements TextBoxLabelInterface {

        TITLE("Title"),
        SUMMARY("Summary"),
        DESCRIPTION("Description"),
        ACTION_TEXT("Action Text"),
        TEXT_COLOR("Text color"),
        TEXT_BACKGROUND("Text background");
        private final String label;

        FsoLabels(final String label) {
            this.label = label;
        }

        public String getDisplayableLabel() {
            return label;
        }
    }

    public static enum EngageNotificationsSettingsLabels implements TextBoxLabelInterface {

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

    public TextBoxLabels() {
        super();
    }
}
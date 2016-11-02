package com.ui.automation.app.eventBus;

import java.util.Formatter;

/**
 * Created with IntelliJ IDEA.
 * User: coheney
 * Date: 13/03/14
 * Time: 16:46
 * To change this template use File | Settings | File Templates.
 */
public class EventType {

    public enum Message {
        ACTION_CLICK("Clicking on"),
        ACTION_DOUBLE_CLICK("Double-Clicking on"),
        ACTION_HOVER("Hovering mouse over"),
        ACTION_HOVER_AND_CLICK("Hovering <b title=\"%s\">element</b> and click <b title=\"%s\">this element</b>"),
        ACTION_SEND_TEXT("Sending text [%s] with Validation [%s] to"),
        ACTION_SEND_FILE("Sending File to"),
        ACTION_SEND_KEYS("Sending Keys"),
        ACTION_SELECT_OPTION("Selecting Option from DropDown"),
        ACTION_CLEAR_TEXT("Clearing Text of"),
        ACTION_DRAG_DROP("Drag and drop"),
        ACTION_SET_CHECKBOX("Setting checkbox value to %s"),
        EXPECT_VISIBLE("Checking Visibility of"),
        EXPECT_NOT_OVERFLOW("Checking not overflow of"),
        EXPECT_NUMERIC_VALUE("Checking if numeric values exist"),
        EXPECT_INVISIBLE("Checking Invisibility (with exist <b title=\"%s\">ELEMENT</b> condition) of"),
        EXPECT_NOT_EXISTS("Checking the Dom not exists (with exist <b title=\"%s\">ELEMENT</b> condition) of"),
        EXPECT_EXISTS("Checking the Dom existence of"),
        EXPECT_SELECTED("Checking Selection of"),
        EXPECT_FOCUSED("Check Element is Focused"),
        EXPECT_NONELECTED("Checking Nonelection of"),
        EXPECT_ENABLED("Checking Element Enabled in"),
        EXPECT_DISABLED("Checking Element Disabled in"),
        EXPECT_MIN_ELEMENT_COUNT("Checking Element Text Integer &gt; [%s] of"),
        EXPECT_EQUALS_ELEMENT_COUNT("Checking Element Count = [%s] of"),
        EXPECT_EQUALS_VISIBLE_ELEMENT_COUNT("Checking count of visible element = [%s] of"),
        EXPECT_ATT_VALUE_CONTAIN("Checking Attribute [%s] contains [%s] in"),
        EXPECT_CSS_VALUE_CONTAIN("Checking CSS property [%s] contains [%s] in"),
        EXPECT_ATT_VALUE_NOT_CONTAIN("Checking Attribute [%s] does not contain [%s] in"),
        EXPECT_TEXT_CONTAINS("Checking Text Contains [%s] in"),
        EXPECT_TEXT_NOT_CONTAINS("Checking Text not Contains [%s] in"),
        EXPECT_ELEMENT_COLLECTION_SIZE("element collection with expected size [%s]"),
        EXPECT_ELEMENT_COLLECTION_SIZE_AT_LEAST("element collection size to be at least [%s]"),
        EXPECT_ANY_ELEMENT_VISIBLE("Any element visible"),
        EXPECT_ALL_ELEMENTS_VISIBLE("All elements visible"),
        EXPECT_TEXT_EQUALS("Checking Text is Equals to [%s] in"),
        EXPECT_ATTRIBUTE_NOT_EXIST("Checking Attribute [%s] Not Exist in"),
        EXPECT_ELEMENTS_TEXT_EQUALITY("Checking text of 2 elements are equals"),
        SPECIFIC_ACTION_SELECT_MENU_ITEM("Selecting Sub Menu Item [%s] in header"),
        REPEATABLE_ACTION("Repeatable action [%s] with expected result [%s]"),
        EXPECT_DATE_IN_RANGE("Checking date [%s] is valid comparing to a range condition"),
        EXPECT_OFF_SCREEN("Checking element is off screen"),
        SCROLL_TO_TOP("Scroll to top");

        private String text;
        private Message(String text) {
            this.text = text;
        }

        String getText() {
            return text;
        }
    }

    private Message message;
    private String[] args = {};

    public EventType(Message message, String... args) {
        this.message = message;
        this.args = args;
    }

    public void setParams(String... args) {
        this.args = args;
    }

    public String value() {
        Formatter f = new Formatter();
        return f.format(message.getText(), this.args).toString();
    }
}

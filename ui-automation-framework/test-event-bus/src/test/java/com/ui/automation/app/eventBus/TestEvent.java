package com.ui.automation.app.eventBus;

import java.util.Map;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: coheney
 * Date: 13/03/14
 * Time: 16:57
 * To change this template use File | Settings | File Templates.
 */
public class TestEvent   {

    private EventType type;
    private String message;
    private Map<String,String> details;
    private String uuid;

    public static String RESOLUTION = "Resolution";
    public static String IS_FAILED = "Is failed";
    public static String TOTAL_TIME = "Total Time";
    public static String EXC_OCCURS = "Exception Occur";
    public static String EXC_CAUSE = "Caused by";
    public static String EXC_STACK_TRACE = "Stack Trace";
    public static String IMAGE = "image";
    public static String EXC_BROWSER_LOG = "Browser Log";
    public static String EXC_PAGE_SRC = "Page HTML Source";

    public TestEvent(EventType type, String message,Map<String,String> details) {
        this.type = type;
        this.message = message;
        this.details = details;
        this.uuid = UUID.randomUUID().toString();
        // Since all details are generically added to report, add uuid to get it printed
        this.details.put("uuid", uuid);
    }

    public EventType getType() {
        return this.type;
    }

    public String getMessage() {
        return this.message;
    }

    public Map<String,String> getDetails(){
        return this.details;
    }

    public String getUuid() {
        return uuid;
    }
}

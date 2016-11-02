package com.ui.automation.common.exception;

/**
 * A general runtime exception for indicating unclassified errors
 */
public class MaasUIAutomationException extends RuntimeException {

    /**
     * See {@link RuntimeException#RuntimeException()}
     */
    public MaasUIAutomationException() {
        super();
    }

    /**
     * See {@link RuntimeException#RuntimeException(String)}
     */
    public MaasUIAutomationException(String message) {
        super(message);
    }

    /**
     * See {@link RuntimeException#RuntimeException(String, Throwable)}
     */
    public MaasUIAutomationException(String message, Throwable cause) {
        super(message, cause);
    }
}

package com.ui.automation.app.listeners.helpers;

public class RoleHolder {
    private final static ThreadLocal<String> threadRole = new ThreadLocal();

    public static void set(String role) {
        threadRole.set(role);
    }
    public static String get() {
        return threadRole.get();
    }
}

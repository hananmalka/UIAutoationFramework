package com.ui.automation.common.junit;

/**
 * Created by peere on 18/12/2014.
 */
public class TestDetails {

    private String testName;
    private boolean tenantType = false;

    /**
     * Sets the test class name
     */
    public void setTestName(String testName) {
        this.testName = testName;
    }

    /**
     * Returns the test class name
     *
     * @return
     */
    public String getTestName() {
        return (testName == null) ? "" : testName;
    }

    /**
     * Sets tenant type
     */
    public void setTrialTenant(boolean tenantType) {
        this.tenantType = tenantType;
    }

    /**
     * Returns trial tenant
     */
    public boolean isTrialTenant() {
        return tenantType;
    }
}

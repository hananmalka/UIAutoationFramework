package com.ui.automation.selenium.wd;

import com.ui.automation.common.exception.MaasUIAutomationException;
import com.ui.automation.common.element.config.TestPropertiesImpl;

/**
 * Created with IntelliJ IDEA.
 * User: coheney
 * Date: 30/03/14
 * Time: 11:24
 * To change this template use File | Settings | File Templates.
 */
public interface Profiling {

    /**
     * Start profiling snapshot for performance<br/>
     * You must set system var {@value TestPropertiesImpl#REMOTE_WEB_DRIVER_URL_PROPERTY}
     */
    void start();
    /**
     * End the current profiling snapshot.<br/>
     * @throws MaasUIAutomationException if profiling not started
     */
    void end();
    void getResults();
}

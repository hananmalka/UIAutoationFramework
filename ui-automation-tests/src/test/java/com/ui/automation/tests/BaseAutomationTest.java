package com.ui.automation.tests;

import com.mqm.automation.ui.services.reports.Reporter;
import com.ui.automation.app.listeners.BrowserManagerListener;
import com.ui.automation.app.listeners.MaasTestContextListener;
import com.ui.automation.app.listeners.ReportCreationListener;
import com.ui.automation.app.listeners.ReportFlushListener;
import com.ui.automation.app.runners.SpringJUnit4ClassReporterRunner;
import com.ui.automation.common.exception.MaasUIAutomationException;
import com.ui.automation.selenium.lifecycle.DriverTestContext;
import com.ui.automation.selenium.wd.Browser;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

// TODO: we need the MaasRunner in order to run the `ReportRunListener`
// in order to remove it we need to move the logic to another listener (maybe ReportCreationListener)
@RunWith(SpringJUnit4ClassReporterRunner.class)
@TestExecutionListeners(listeners = {ReportCreationListener.class, MaasTestContextListener.class, BrowserManagerListener.class, ReportFlushListener.class})
@ContextConfiguration(locations = {"classpath:/META-INF/spring/ui-automation-tests-context.xml"})
public abstract class BaseAutomationTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    protected Reporter reporter;

    @Autowired
    private DriverTestContext driverTestContext;

    private String createTenantUrlPattern = "<scheme>://<server>:<port>/portal/service/v1/tenant/create?tenantName=<tenantName>&loginName=<userName>";
    private String uiAuthenticateUrlPattern = "<scheme>://<server>:<port>/portal/msg/actions/doLogin.action?username=<userName>&tenantId=<tenantId>&password=&LWAP_REQ=null";
    private String uiProjectUrlPattern = "<scheme>://<server>:<port>/ui/?p=<sharedspaceId>/<workspaceId>";
    private String rand = RandomStringUtils.randomAlphabetic(4);
    private String userName = rand + "@hpe.com";
    private String tenantName = "tenant_name_" + rand;
    private String tenantId = "";
    private String scheme = "http";
    private String sharedspaceId = "";
    private String workspaceId = "";

    // RUN ON LOCAL SERVER
    private String server = "localhost";
    private String port = "8080";

    // RUN ON EXT SERVER
//    private String server = "myd-vm19883.hpswlabs.adapps.hp.com";
//    private String port = "8081";

    private static boolean beforeClassMethodsCalled = false;

    @Rule
    public TestRule nonStaticBeforeClass = new TestWatcher() {

        private List<Method> getNonStaticBeforeClassMethods(Class clazz) {
            List<Method> beforeClassMethods = new ArrayList<Method>();
            for (Method method : clazz.getMethods()) {
                if (method.isAnnotationPresent(NonStaticBeforeClass.class)) {
                    beforeClassMethods.add(0, method);
                }
            }
            return beforeClassMethods;
        }

        @Override
        protected void starting(Description description) {
            if (beforeClassMethodsCalled) {
                return;
            }
            List<Method> beforeClassMethods = getNonStaticBeforeClassMethods(BaseAutomationTest.this.getClass());
            for (Method beforeClassMethod : beforeClassMethods) {
                try {
                    beforeClassMethod.invoke(BaseAutomationTest.this);
                } catch (ReflectiveOperationException e) {
                    throw new MaasUIAutomationException("Failed executing NonStaticBeforeClass method: " + beforeClassMethod.getName(), e);
                }
            }
            beforeClassMethodsCalled = true;
        }
    };

    @NonStaticBeforeClass
    public void authenticate() {
        uiAuthenticate();
    }

    private void createTenant() {
    }

    private void restAuthenticate() {
    }

    // TODO: DANA - ALEX - is this the right way to login in the browser
    private void uiAuthenticate() {
        Browser browser = driverTestContext.getDriver().browser();
        driverTestContext.getDriver().browser().get("https://authentication-service-autoqa.isappcloud.com/?origin=cm-autoqa");
        String username = getProperty("config.properties", "defaultUsername");
        String password = getProperty("config.properties", "defaultPassword");
        UI.loginPage.performLogin(username, password);
    }

    private void delay(long millis)  {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String getUrl(String urlPattern) {
        String url = urlPattern.replace("<scheme>", scheme);
        url = url.replace("<server>", server);
        url = url.replace("<port>", port);
        url = url.replace("<tenantId>", tenantId);
        url = url.replace("<tenantName>", tenantName);
        url = url.replace("<userName>", userName);
        url = url.replace("<sharedspaceId>", sharedspaceId);
        url = url.replace("<workspaceId>", workspaceId);

        return url;
    }


    private static String getProperty(String fileName, String property) {
        String result = "";
        Properties prop = new Properties();
        InputStream input = null;
        try {
            String accountsFilePath = ClassLoader.getSystemResource(fileName).getFile();
            input = new FileInputStream(accountsFilePath);

            // load a properties file
            prop.load(input);

            result = prop.getProperty(property);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}


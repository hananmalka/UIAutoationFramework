package com.ui.automation.selenium.browser;

import com.mqm.automation.ui.services.reports.Reporter;
import com.ui.automation.common.element.items.SpecialKeys;
import com.ui.automation.selenium.lifecycle.DriverTestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Component
public class BrowserPageImpl implements BrowserPage {

    @Autowired
    private DriverTestContext context;

    @Autowired
    private AlertPage alertPage;

    @Autowired
    Reporter reporter;


    public void open(String browserType) {
        context.getDriver().browser().open(browserType);

        //get the remote host node and put it on reporter
        int remotePort = context.getDriver().getRemoteSeleniumNodePort();
        String remotePortStr = remotePort == -1 ? "" : ":" + remotePort;
        String nodeUrl = context.getDriver().getRemoteSeleniumNodeHost() + remotePortStr;
        Map<String, String> reporterHeaderMap = new HashMap<String, String>();
        reporterHeaderMap.put("Remote Host", nodeUrl);
        long browserTime = context.getDriver().getCurrentRemoteCreationTimeMS();
        if (browserTime != -1 && browserTime < 1000) {
            reporterHeaderMap.put("Remote Browser Creation", "Less than 1 second");
        } else if (browserTime >= 1000) {
            reporterHeaderMap.put("Remote Browser Creation Time (Sec)", String.valueOf(browserTime / 1000));
        }
        reporter.testClassHeader(reporterHeaderMap);
    }

    public void quit() {
        context.getDriver().browser().quit();
    }

    public void close() {
        context.getDriver().browser().close();
    }

//    @Override
//    public void get(String url) {
//        context.getDriver().browser().get(url);
//    }

    public void savePageSource(Path targetDir, String fileName) {
        context.getDriver().browser().savePageSource(targetDir, fileName);
    }

    public void saveConsoleLog(Path targetDir, String fileName) {
        context.getDriver().browser().saveConsoleLog(targetDir, fileName);
    }

    public void maximize() {
        context.getDriver().browser().maximize();
    }

    public void saveScreenshot(Path targetDir, String path) {
        context.getDriver().browser().saveScreenshot(targetDir, path);
    }

    public String getBrowserName() {
        return context.getDriver().browser().getBrowserName();
    }

    public String getCurrentURL() {
        return context.getDriver().browser().getCurrentURL();
    }

    public void back() {
        context.getDriver().browser().back();
    }

    public AlertPage alert() {
        return this.alertPage;
    }

    public void consoleLog(String text) {
        context.getDriver().actions().browserConsoleLog(text);
    }

    public void refresh() {
        context.getDriver().browser().refresh();
    }

    public void startProfiling() {
        context.getDriver().browser().profiling().start();

    }

    public void endProfiling() {
        context.getDriver().browser().profiling().end();
    }

    public boolean isAtTopOfScroll() {
        return context.getDriver().browser().isAtTopOfScroll();
    }

    public int getTimeZoneOffset() {
        return context.getDriver().browser().getTimeZoneOffset();
    }

    public boolean isDateInDst(int year, int month, int day, int hour) {
        return context.getDriver().browser().isDateInDst(year, month, day, hour);
    }

    public void pressKeyboard(SpecialKeys key) {
        context.getDriver().browser().pressKey(key);
    }

    public long getTime() {
        return context.getDriver().browser().getTime();
    }

    public void closeCurrentBrowserTab() {
        context.getDriver().browser().closeCurrentTab();
    }
}

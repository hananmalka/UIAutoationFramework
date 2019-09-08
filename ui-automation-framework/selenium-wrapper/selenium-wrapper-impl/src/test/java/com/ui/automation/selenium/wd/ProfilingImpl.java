package com.ui.automation.selenium.wd;

import com.mqm.automation.ui.services.execution.ApplicationContextHolder;
import com.ui.automation.common.element.config.TestProperties;
import com.ui.automation.common.exception.MaasUIAutomationException;
import com.ui.automation.selenium.service.WebPageTest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: coheney
 * Date: 30/03/14
 * Time: 11:25
 * To change this template use File | Settings | File Templates.
 */

public class ProfilingImpl implements Profiling {

    private DriverServices driverServices;
    private final String START_PROF_MESSAGE = "__profiling_start__";
    private final String END_PROF_MESSAGE = "__profiling_end__";
    private final String START_PROF__TEST_MESSAGE = "__per_test_start__";
    private final String END_PROF__TEST_MESSAGE = "__per_test_end__";


    public ProfilingImpl(DriverServices driverServices) {
        this.driverServices = driverServices;
    }

    @Override
    public void start() {
        reportProfiling(START_PROF_MESSAGE);
    }

    @Override
    public void end() {
        reportProfiling(END_PROF_MESSAGE);
    }

    @Override
    public void getResults() {
        //https://gist.github.com/klepikov/5457750
        Logs logs = driverServices.getDriver().manage().logs();
        String name = "XXXXX";
        String location = "YYYYY";
        String webPageTestUrl = "http://ZZZZZ:8888";
        List<LogEntryDetails> entryDetailsList = new ArrayList<>();
        if (logs.getAvailableLogTypes().contains(LogType.PERFORMANCE)) {
            List<LogEntry> mainEntries = logs.get(LogType.PERFORMANCE).getAll();
            System.out.println("Found total of " + mainEntries.size() + " performance log entries");
            JSONArray currentList = null;
            JSONArray perTestList = null;
            for (LogEntry entry : mainEntries) {
                try {
                    if (entry.getMessage().contains(START_PROF__TEST_MESSAGE)) {
                        perTestList = new JSONArray();
                    } else if (entry.getMessage().contains(START_PROF_MESSAGE)) {
                        currentList = new JSONArray();
                    } else if (entry.getMessage().contains(END_PROF_MESSAGE) && currentList != null) {
                        byte[] screenshot = ((TakesScreenshot) driverServices.getDriver()).getScreenshotAs(OutputType.BYTES);
                        if (currentList.length() > 0) {
                            entryDetailsList.add(new LogEntryDetails(currentList,screenshot,name,location));

                        }
                        currentList = null;
                    } else if (entry.getMessage().contains(END_PROF__TEST_MESSAGE) && perTestList != null) {
                        byte[] screenshot = ((TakesScreenshot) driverServices.getDriver()).getScreenshotAs(OutputType.BYTES);
                        if (currentList.length() > 0) {
                            entryDetailsList.add(new LogEntryDetails(perTestList,screenshot,name,location));
                        }
                        perTestList = null;
                    } else if (currentList != null && perTestList != null) {
                        JSONObject message = new JSONObject(entry.getMessage());
                        JSONObject devToolsMessage = message.getJSONObject("message");
                        currentList.put(devToolsMessage);
                        perTestList.put(devToolsMessage);
                    } else if (currentList != null) {
                        JSONObject message = new JSONObject(entry.getMessage());
                        JSONObject devToolsMessage = message.getJSONObject("message");
                        currentList.put(devToolsMessage);

                    }
                    else if (perTestList != null) {
                        JSONObject message = new JSONObject(entry.getMessage());
                        JSONObject devToolsMessage = message.getJSONObject("message");
                        currentList.put(devToolsMessage);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        for (LogEntryDetails entryDetails: entryDetailsList){
            try {
                String resultUrl = new WebPageTest(new URL(webPageTestUrl), entryDetails.location, entryDetails.name).
                        submitResult(entryDetails.jsonArray,entryDetails.screenshot);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void reportProfiling(String message) {
        TestProperties testProperties = ApplicationContextHolder.getApplicationContext().getBean(TestProperties.class);
        if (!testProperties.isPerformanceTest()) {
            throw new MaasUIAutomationException("Unable to do browser 'profiling'! You must enable performance logs by setting system property [" + testProperties.getPerformancePropertyName() + "=true]");
        }
        driverServices.executeJavaScript("console.timeStamp('" + message + "')");
    }


    private class LogEntryDetails {
        JSONArray jsonArray;
        byte[] screenshot;
        String name;
        String location;
        public LogEntryDetails(JSONArray jsonArray,byte[] screenshot,String name,String location){
            this.jsonArray = jsonArray;
            this.screenshot = screenshot;
            this.name = name;
            this.location = location;
        }
    }


}

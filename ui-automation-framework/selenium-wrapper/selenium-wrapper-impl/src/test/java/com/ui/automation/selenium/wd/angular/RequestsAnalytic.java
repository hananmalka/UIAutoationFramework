package com.ui.automation.selenium.wd.angular;

import com.mqm.automation.ui.services.reports.Reporter;
import com.ui.automation.selenium.lifecycle.DriverTestContext;
import com.ui.automation.selenium.wd.DriverServices;
import com.ui.automation.selenium.wd.MaasDriverImpl;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: coheney
 * Date: 15/05/14
 * Time: 16:06
 * To change this template use File | Settings | File Templates.
 */
@Component
public class RequestsAnalytic {

    @Autowired
    private DriverTestContext driverTestContext;

    @Autowired
    protected Reporter reporter;

    public void start(){


        String script = "window.automationHttpStart = true;\n"
                + "      window.automationHTTPList = {};\n"
                + "      return true;";
                getDriverServices().executeJavaScript(script);

    }

    public void stop(){
        String script = "window.automationHttpStart = false";
        getDriverServices().executeJavaScript(script);
    }

    public void report(){
        reporter.debug("Requests analytic started");

        AngularHTTPRequest results = getResults();
        reporter.debug("Average Request data size: " + results.getAvgRequestsDataSize() + " bytes");
        reporter.debug("Average Request Headers size: " + results.getAvgRequestsHeaderSize() + " bytes");
        reporter.debug("Average Response data size: " + results.getAvgResponsesDataSize() + " bytes");
        reporter.debug("Average Request headers size: " + results.getAvgResponsesHeaderSize() + " bytes");
        reporter.debug("Average RTT: " + results.getAvgRTT());
        JSONObject biggestDataResponseObj = results.getBiggestDataResponseObj();
        JSONObject longestRTTObj = results.getLongestRTTObj();
        try{
            reporter.debug("Longest RTT: " + longestRTTObj.get("rtt"));
            reporter.debug("Longest RTT URL: " + longestRTTObj.get("requestUrl"));
            reporter.debug("Biggest Response Length: " + biggestDataResponseObj.get("responseDataLength"));
            reporter.debug("Biggest Response Length URL: " + biggestDataResponseObj.get("requestUrl"));
        } catch (Exception ex){
            // Ignore exception - this shouldn't fail the test
            reporter.warning("Failed to get performance data - " + ex.getMessage());
        }
        reporter.debug("Number of GET Requests: " + results.getNumberOfGetRequests());
        reporter.debug("Number of POST Requests: " + results.getNumberOfPostRequests());
        reporter.debug("Total requests data size(POST): " + results.getTotalRequestsDataSize() + " bytes");
        reporter.debug("Total responses data size: " + results.getTotalResponsesDataSize() + " bytes");
        reporter.debug("Total requests headers size: " + results.getTotalRequestsHeaderSize() + " bytes");
        reporter.debug("Total responses headers size: " + results.getTotalResponsesHeaderSize() + " bytes");
        reporter.debug("Total requests: " + results.getTotalRequests());

        StringBuilder msg = new StringBuilder();
        Map<Integer,Integer> statusMap = results.getResponsesStatus();
        for (Map.Entry<Integer, Integer> entry : statusMap.entrySet()) {
            msg.append("[" + entry.getKey() + "," + entry.getValue() +"] ");
        }
        reporter.debug("All Responses status: " + msg);
    }

	public void expectPostRequestCounter(int counter, String urlRegex, HttpRequestPayloadValidator payloadValidator) {
		int postRequestCount = getResults().getPostRequestCount(urlRegex, payloadValidator);
		assertEquals("post requests counter exceeded", counter, postRequestCount);
	}

    private AngularHTTPRequest getResults(){

        String jsonString = (String)getDriverServices().executeJavaScript("return JSON.stringify(window.automationHTTPList);");

        JSONObject json = null;
        if (jsonString == null) {
            jsonString = "{}"; // Set to empty JSON object to avoid NPE
        }
        try {
            json = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return new AngularHTTPRequest(json);
    }

    private DriverServices getDriverServices() {
        return ((MaasDriverImpl) driverTestContext.getDriver()).getDriverServices();
    }
}

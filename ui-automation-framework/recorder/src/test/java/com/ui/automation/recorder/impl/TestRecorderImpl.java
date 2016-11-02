package com.ui.automation.recorder.impl;

import com.ui.automation.common.element.config.TestProperties;
import com.ui.automation.selenium.lifecycle.DriverTestContext;
import com.ui.automation.recorder.api.TestRecorder;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.runner.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.logging.Logger;

/**
 * Author: Gil Tzadikevitch
 * Date: 24/04/14
 */
@Component
public class TestRecorderImpl implements TestRecorder {


    public static final String REST_RECORD_SAVE_AND_GET = "/rec/save-and-get";
    public static final String REST_RECORD_STOP = "/rec/stop";
    public static final String REST_RECORD_START = "/rec/start";
    public static final String REST_RECORD_UPDATE = "/rec/update";

    @Autowired
    private TestProperties testProperties;

    @Autowired
    private DriverTestContext driverTestContext;


    private Logger logger = Logger.getLogger(getClass().getName());

    private String getRemoteDriverHost() {
        return driverTestContext.getDriver().getRemoteSeleniumNodeHost();
    }

    private int getRemoteDriverPort() {
        return driverTestContext.getDriver().getRemoteSeleniumNodePort();
    }

    @Override
    public void startRecording(Description description) throws Exception {
        if (ignoreRecordingAction()) {
            return;
        }
        String remoteNodeHost = getRemoteDriverHost();
        if (remoteNodeHost != null) {
            String line1 = "Test Description: " + description.getDisplayName() + "       Selenium Node: " + remoteNodeHost;
            HttpResponse response = executeRecorderCall(remoteNodeHost, REST_RECORD_START, line1, "", "", getRemoteDriverPort());
            logger.info("Started remote recording -  response code: " + response.getStatusLine().getStatusCode());
        }

    }

    @Override
    public void updateRecording(final String line1, final String line2, final String line3) {
        if (ignoreRecordingAction()) {
            return;
        }

        try {
            final String remoteNodeHost = getRemoteDriverHost();
            final int remoteDriverPort = getRemoteDriverPort();
            if (remoteNodeHost != null) {
//                long start = System.currentTimeMillis();
                HttpResponse response = executeRecorderCall(remoteNodeHost, REST_RECORD_UPDATE, line1, line2, line3, remoteDriverPort);
//                long end = System.currentTimeMillis();
//                logger.info("updated remote recording report lines (in " + (end - start) + " ms) -  response code: " + response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Failed updating recorder report lines");
        }


    }

    @Override
    public void stopAndDeleteRecording() throws Exception {
        if (ignoreRecordingAction()) {
            return;
        }
        String remoteNodeHost = getRemoteDriverHost();
        if (remoteNodeHost != null) {
            long startTime = System.currentTimeMillis();
            HttpResponse response = executeRecorderCall(remoteNodeHost, REST_RECORD_STOP, "", "", "", getRemoteDriverPort());
            long duration = System.currentTimeMillis() - startTime;
            logger.info("Stopped remote recording - response code: " + response.getStatusLine().getStatusCode() + " took " + duration + " millis");
        }
    }

    @Override
    public boolean stopAndSaveRecording(Path targetFolder, String filename) throws Exception {
        if (ignoreRecordingAction()) {
            return false;
        }

        String remoteNodeHost = getRemoteDriverHost();
        if (remoteNodeHost != null) {
            long startTime = System.currentTimeMillis();
            HttpResponse response = executeRecorderCall(remoteNodeHost, REST_RECORD_SAVE_AND_GET, "", "", "", getRemoteDriverPort());
            if (response.getStatusLine().getStatusCode() == 200) {
                InputStream content = response.getEntity().getContent();
                File destination = new File(targetFolder.toFile(), filename);
                FileUtils.copyInputStreamToFile(content, destination);
                return FileUtils.sizeOf(destination) > 1000;
            }
            long duration = System.currentTimeMillis() - startTime;
            logger.info("Stopped & Saved remote recording -  response code: " + response.getStatusLine().getStatusCode() + " took " + duration + " millis");
        }
        return false;
    }

    private boolean ignoreRecordingAction() {
        return !testProperties.isAutoRecording() || testProperties.getRemoteDriverURL() == null || driverTestContext.getDriver() == null;
    }

    private HttpResponse executeRecorderCall(String remoteNodeHost, String restCall, String reportLine1,
                                             String reportLine2, String reportLine3, int remoteDriverPort) throws IOException {
        HttpClient client = new DefaultHttpClient();
        StringBuilder url = new StringBuilder();
        url.append("http://");
        url.append(remoteNodeHost);
        url.append(":").append(String.valueOf(remoteDriverPort));
        url.append("/extra/");
        url.append("VideoRecoderServlet");
        HttpPost post = new HttpPost(url.toString());
        post.addHeader("RecorderCommand", restCall);
        if (reportLine1 != null) {
            post.addHeader("ReportLine1", reportLine1);
        }
        if (reportLine2 != null) {
            post.addHeader("ReportLine2", reportLine2);
        }
        if (reportLine3 != null) {
            post.addHeader("ReportLine3", reportLine3);
        }
        return client.execute(post);

    }
}

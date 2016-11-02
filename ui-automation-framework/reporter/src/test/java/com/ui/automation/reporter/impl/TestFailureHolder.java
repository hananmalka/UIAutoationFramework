package com.ui.automation.reporter.impl;

import org.junit.runner.notification.Failure;

import java.nio.file.Path;

/**
 * Created with IntelliJ IDEA.
 * User: coheney
 * Date: 24/03/14
 * Time: 12:11
 * To change this template use File | Settings | File Templates.
 */
public class TestFailureHolder {

    public Failure failure = null;
    public String imagePath = "";
    public String browserConsolePath = "";
    public String recordingPath = "";
    public String htmlSrcPath = "";

    public TestFailureHolder(){ }

    public TestFailureHolder(Failure failure, String imagePath, String browserConsolePath, String recordingPath, String htmlSrcPath) {
        this.failure = failure;
        this.imagePath = imagePath;
        this.browserConsolePath = browserConsolePath;
        this.recordingPath = recordingPath;
        this.htmlSrcPath = htmlSrcPath;
    }
}

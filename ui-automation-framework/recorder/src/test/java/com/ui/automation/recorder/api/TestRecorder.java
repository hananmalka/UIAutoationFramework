package com.ui.automation.recorder.api;

import org.junit.runner.Description;

import java.nio.file.Path;

/**
 * Author: Gil Tzadikevitch
 * Date: 24/04/14
 */
public interface TestRecorder {


    void startRecording(Description description) throws Exception;

    void stopAndDeleteRecording() throws Exception;

    boolean stopAndSaveRecording(Path targetFolder, String filename) throws Exception;

    public void updateRecording(String line1, String line2, String line3);
}

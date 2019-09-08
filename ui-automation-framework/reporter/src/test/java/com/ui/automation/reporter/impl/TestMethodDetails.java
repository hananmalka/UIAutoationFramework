package com.ui.automation.reporter.impl;

import com.hp.gagawa.java.Node;
import com.hp.gagawa.java.elements.*;
import com.mqm.automation.ui.services.execution.UnstableTest;
import com.ui.automation.reporter.api.TestDoc;
import com.ui.automation.reporter.api.TestStatus;
import org.junit.Ignore;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: coheney
 * Date: 04/03/14
 * Time: 15:12
 * To change this template use File | Settings | File Templates.
 */
public class TestMethodDetails {
    Date startTime = null;
    Date endTime = null;
    String name = "";
    String testDocName = "";
    String testDocDescription = "";
    long totalExecutionSecTime = 0;
    List<ReportMessage> messages = new ArrayList<>();
    TestStatus status = TestStatus.SUCCEED;
    String assum_failure_exception = "";
    Boolean isAutoStepFailed = false;
    List<TestFailureHolder> failures = new ArrayList<>();
    String ignoreValue = "";


    public TestMethodDetails() {
    }

    public TestMethodDetails(String name) {
        this.name = name;
    }


    public void testIgnored(Description description) {
        this.status = TestStatus.IGNORED;
        this.name = description.getMethodName();
        TestDoc tdoc = description.getAnnotation(TestDoc.class);
        if (tdoc != null) {
            this.testDocName = tdoc.name();
            this.testDocDescription = tdoc.description();
        }
        Ignore ignore = description.getAnnotation(Ignore.class);
        if (ignore != null) {
            this.ignoreValue = ignore.value();
        }

    }


    public void testAssumptionFailure(Failure failure) {
        this.status = TestStatus.ASSUMPTION_FAILURE;
        this.name = failure.getDescription().getMethodName();
        this.startTime = new Date();
        TestDoc tdoc = failure.getDescription().getAnnotation(TestDoc.class);
        this.assum_failure_exception = failure.getMessage();
        if (tdoc != null) {
            this.testDocName = tdoc.name();
            this.testDocDescription = tdoc.description();
        }
    }

    public void testStart(Description description) {
        startTime = new Date();
        this.name = description.getMethodName();

        TestDoc tdoc = description.getAnnotation(TestDoc.class);
        if (tdoc != null) {
            this.testDocName = tdoc.name();
            this.testDocDescription = tdoc.description();
        }

        Class<?> clazz = description.getTestClass();
        if (clazz.getAnnotation(UnstableTest.class) != null || description.getAnnotation(UnstableTest.class) != null) {
            this.status = TestStatus.UNSTABLE_SUCCEED;
        }
    }

    public void testEnd(Description description) {
        endTime = new Date();
        this.totalExecutionSecTime = (this.endTime.getTime() - this.startTime.getTime()) / 1000;
    }

    public void testFailure(TestFailureHolder testFailureHolder) {
        this.failures.add(testFailureHolder);
        if (this.status.equals(TestStatus.UNSTABLE_SUCCEED)) {
            this.status = TestStatus.UNSTABLE_FAILED;
        } else {
            this.status = TestStatus.FAILED;
        }
    }


    public Li getHtmlTreeNode(String contentId) {
        Li treeNode = new Li();
        A aLink = new A();
        aLink.setAttribute("onclick", "selectview(this)");
        aLink.setAttribute("data-cont", "content-" + contentId);
        treeNode.setTitle(name + " - " + status);

        aLink.appendText(name);
        treeNode.appendChild(aLink);
        String img = "";
        switch (this.status) {
            case FAILED:
                img = CssProperties.IMG_FAIL.value();
                break;
            case IGNORED:
                img = CssProperties.IMG_IGNORE.value();
                break;
            case ASSUMPTION_FAILURE:
                img = CssProperties.IMG_ASS_FAIL.value();
                break;
            case SUCCEED:
                img = CssProperties.IMG_SUCCESS.value();
                break;
            case UNSTABLE_FAILED:
                img = CssProperties.IMG_UNSTABLE_FAIL.value();
                break;
            case UNSTABLE_SUCCEED:
                img = CssProperties.IMG_UNSTABLE_SUCCEED.value();
                break;
        }
        aLink.setCSSClass(CssProperties.ELLIPSIS_CLASS.value() + " " + img);
        return treeNode;
    }

    public Div getHtmlFlowContent(String contentId) {
        Div mainFlow = new Div();
        mainFlow.setCSSClass("flow-content");
        mainFlow.setId("content-" + contentId);
        mainFlow.setStyle("display: none;");
        Div header = new Div();
        header.appendText(name);
        header.setCSSClass("margin-top:20px;");
        Span spId = new Span();
        spId.setStyle("float: right;");
        spId.appendText("ID: " + contentId);
        mainFlow.appendChild(spId);

        mainFlow.appendChild(header);

        Div flow = new Div();
        flow.setCSSClass("flow");

        if (this.status != TestStatus.IGNORED) {
            flow.appendChild(this.htmlGetShowHideSteps());
        }
        Div testSummary = getTestSummary();

        if (testSummary.children.size() > 0) {
            mainFlow.appendChild(testSummary);
        }
        mainFlow.appendChild(flow);
        flow.appendChild(this.htmlGetStepsTable(messages));
        String headerClass = " img-text content-header ";
        switch (this.status) {
            case FAILED:
                header.setCSSClass(CssProperties.IMG_FAIL.value() + " img-text content-header " + CssProperties.ELLIPSIS_CLASS.value());
                header.setTitle("Test " + this.name + " Failed");
                if (isAutoStepFailed) {
                    flow.appendChild(this.htmlGetFailure(this.failures.get(0)));
                } else {
                    for (TestFailureHolder failureHolder : this.failures) {
                        flow.appendChild(this.htmlGetFailure(failureHolder));
                    }
                }
                break;

            case IGNORED:
                header.setCSSClass(CssProperties.IMG_IGNORE.value() + headerClass + CssProperties.ELLIPSIS_CLASS.value());
                String igReason = this.ignoreValue.trim().equals("") ? "No Reason Found" : this.ignoreValue.trim();
                header.appendText(name + "<br><b>Ignore Reason: </b> " + igReason);
                header.setTitle("Test " + this.name + " Ignored");

                break;

            case ASSUMPTION_FAILURE:
                header.setCSSClass(CssProperties.IMG_ASS_FAIL.value() + headerClass + CssProperties.ELLIPSIS_CLASS.value());
                header.setTitle("Test " + this.name + " Assumption Failure");
                break;
            case UNSTABLE_FAILED:
                header.setCSSClass(CssProperties.IMG_UNSTABLE_FAIL.value() + headerClass + CssProperties.ELLIPSIS_CLASS.value());
                header.setTitle("Test " + this.name + " Unstable Status - FAILED");
                if (isAutoStepFailed) {
                    flow.appendChild(this.htmlGetFailure(this.failures.get(0)));
                } else {
                    for (TestFailureHolder failureHolder : this.failures) {
                        flow.appendChild(this.htmlGetFailure(failureHolder));
                    }
                }
                break;
            case UNSTABLE_SUCCEED:
                header.setCSSClass(CssProperties.IMG_UNSTABLE_SUCCEED.value() + headerClass + CssProperties.ELLIPSIS_CLASS.value());
                header.setTitle("Test " + this.name + " Unstable Status = SUCCEED");
                break;
            case SUCCEED:
                header.setCSSClass(CssProperties.IMG_SUCCESS.value() + headerClass + CssProperties.ELLIPSIS_CLASS.value());
                header.setTitle("Test " + this.name + " Succeed");
                break;
        }


        return mainFlow;
    }

    private com.hp.gagawa.java.elements.Object createEmbeddedRecordingViewer(String videoURL) {
        com.hp.gagawa.java.elements.Object embeddedObj = new com.hp.gagawa.java.elements.Object();
        embeddedObj.setClassid("CLSID:22d6f312-b0f6-11d0-94ab-0080c74c7e95");
        embeddedObj.setId("mediaPlayer1");
        embeddedObj.setWidth("180");
        embeddedObj.setHeight("50");
        embeddedObj.setCodebase("http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=5,1,52,701");
        embeddedObj.setStandby("Loading Microsoft Windows Media Player components...'");
        embeddedObj.setType("application/x-oleobject");
        embeddedObj.appendChild(new Param("fileName").setValue(videoURL));
        embeddedObj.appendChild(new Param("animationatStart").setValue("true"));
        embeddedObj.appendChild(new Param("transparentatStart").setValue("true"));
        embeddedObj.appendChild(new Param("autoStart").setValue("false"));
        embeddedObj.appendChild(new Param("showControls").setValue("true"));
        embeddedObj.appendChild(new Param("ShowAudioControls").setValue("false"));
        embeddedObj.appendChild(new Param("ShowStatusBar").setValue("true"));
        embeddedObj.appendChild(new Param("loop").setValue("false"));
        Node embed = new Node("EMBED") {
        };
        embed.setAttribute("pluginspage", "http://microsoft.com/windows/mediaplayer/en/download/");
        embed.setAttribute("id", "mediaPlayer");
        embed.setAttribute("name", "mediaPlayer");
        embed.setAttribute("displaysize", "4");
        embed.setAttribute("autosize", "-1");
        embed.setAttribute("bgcolor", "darkblue");
        embed.setAttribute("showcontrols", "true");
        embed.setAttribute("showtracker", "-1");
        embed.setAttribute("showdisplay", "0");
        embed.setAttribute("showstatusbar", "-1");
        embed.setAttribute("videoborder3d", "-1");
        embed.setAttribute("width", "252");
        embed.setAttribute("height", "228");
        embed.setAttribute("src", videoURL);
        embed.setAttribute("autostart", "false");
        embed.setAttribute("designtimesp", "5311");
        embed.setAttribute("loop", "false");
        embed.setAttribute("type", "application/x-mplayer2");
        embeddedObj.appendChild(embed);

        /**
         * <OBJECT id='mediaPlayer1' width="180" height="50"
         classid='CLSID:22d6f312-b0f6-11d0-94ab-0080c74c7e95'
         codebase='http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=5,1,52,701'
         standby='Loading Microsoft Windows Media Player components...' type='application/x-oleobject'>
         <param name='fileName' value="Stream URL or Full File Path Goes Here">
         <param name='animationatStart' value='true'>
         <param name='transparentatStart' value='true'>
         <param name='autoStart' value="false">
         <param name='showControls' value="true">
         <param name ="ShowAudioControls"value="true">
         <param name="ShowStatusBar" value="true">
         <param name='loop' value="false">
         <EMBED type='application/x-mplayer2'
         pluginspage='http://microsoft.com/windows/mediaplayer/en/download/'
         id='mediaPlayer' name='mediaPlayer' displaysize='4' autosize='-1'
         bgcolor='darkblue' showcontrols="true" showtracker='-1'
         showdisplay='0' showstatusbar='-1' videoborder3d='-1' width="420" height="380"
         src="Stream URL or Full File Path Goes Here" autostart="true" designtimesp='5311' loop="false">
         </EMBED>
         </OBJECT>
         <br />
         <a href="Stream URL or Full File Path Goes Here">Click here for standalone player</a>

         */return embeddedObj;
    }

    public Tr getHtmlTestListRow(String contentId) {
        Tr testRow = new Tr();
        testRow.setAttribute("onclick", "selectview(this)");
        testRow.setAttribute("data-cont", "content-" + contentId);
        testRow.setTitle("Click to open test result");
        Td cell1 = new Td();
        Div name = new Div();
        name.appendText(this.name);
        cell1.setTitle(this.name);
        switch (this.status) {
            case FAILED:
                name.setCSSClass(CssProperties.IMG_FAIL.value());
                break;
            case IGNORED:
                name.setCSSClass(CssProperties.IMG_IGNORE.value());
                break;
            case ASSUMPTION_FAILURE:
                name.setCSSClass(CssProperties.IMG_ASS_FAIL.value());
                break;
            case SUCCEED:
                name.setCSSClass(CssProperties.IMG_SUCCESS.value());
                break;
            case UNSTABLE_SUCCEED:
                name.setCSSClass(CssProperties.IMG_UNSTABLE_SUCCEED.value());
                break;
            case UNSTABLE_FAILED:
                name.setCSSClass(CssProperties.IMG_UNSTABLE_FAIL.value());
                break;
        }

        cell1.appendChild(name);
        testRow.appendChild(cell1);

        Td cell2 = new Td();
        cell2.setStyle("width:30%;");
        Div failDiv = new Div();
        cell2.appendChild(failDiv);
        if (failures.size() != 0) {
            for (TestFailureHolder holder : failures) {
                if (holder.failure != null) {
                    Div innerFailDiv = new Div();
                    innerFailDiv.setCSSClass("failure-div");
                    innerFailDiv.appendText(holder.failure.getMessage());
                    failDiv.appendChild(innerFailDiv);
                }
            }
        }
        testRow.appendChild(cell2);

        Td cell3 = new Td();
        cell3.appendText("");
        testRow.appendChild(cell3);

        Td cell4 = new Td();
        cell4.appendText(String.valueOf(this.totalExecutionSecTime));
        testRow.appendChild(cell4);

        // this.tableOfTestList.appendChild(testRow);
        return testRow;
    }

    private Div getTestSummary() {
        Div testSummary = new Div();
        testSummary.setCSSClass("test-summary");
        if (!testDocName.equals("")) {
            Div testDoc = new Div();
            testDoc.appendText("<b>Test Name: </b>" + testDocName);
            testSummary.appendChild(testDoc);
        }
        if (!testDocDescription.equals("")) {
            Div testDoc = new Div();
            testDoc.appendText("<b>Test Description: </b>" + testDocDescription);
            testSummary.appendChild(testDoc);
        }

        if (this.startTime != null && this.endTime != null) {
            Div times = new Div();
            times.appendText("<b>Start Time: </b> " + this.getHour(startTime) + " <b>End Time: </b> " + this.getHour(endTime) + " <b>Total Time(Sec): </b> " + totalExecutionSecTime);
            testSummary.appendChild(times);
        }
        if (!assum_failure_exception.equals("")) {
            Div testDoc = new Div();
            testDoc.appendText("<b style=\"color:red\">Test Failure: </b>" + assum_failure_exception);
            testSummary.appendChild(testDoc);
        }

        return testSummary;

    }


    private Div htmlGetFailure(TestFailureHolder testFailureHolder) {
        Div div = new Div();
        if (isAutoStepFailed && testFailureHolder.recordingPath.trim().equals("")) {
            return div;
        }

        div.setCSSClass("failure-container");
        Div logs = new Div();
        logs.setCSSClass("test-summary-logs");
        div.appendChild(logs);

        if (!this.isAutoStepFailed) {
            if (!testFailureHolder.browserConsolePath.trim().equals("")) {
                logs.appendChild(getFailureBrowserConsoleLink(testFailureHolder.browserConsolePath));
            }
            if (!testFailureHolder.htmlSrcPath.trim().equals("")) {
                logs.appendChild(getFailureHtmlSource(testFailureHolder.htmlSrcPath));
            }
            if (!testFailureHolder.imagePath.trim().equals("")) {
                div.appendChild(getFailureImage(testFailureHolder.imagePath));
            }
            if (testFailureHolder.failure != null && testFailureHolder.failure.getException() != null) {
                div.appendChild(this.getFailureHeaderText(testFailureHolder.failure.getMessage()));
                div.appendChild(this.getFailureCause(testFailureHolder.failure.getException().getCause()));
                div.appendChild(this.getFailureStackTraceText(testFailureHolder.failure));
            }
        }

        if (!testFailureHolder.recordingPath.trim().equals("")) {
            logs.appendChild(getFailureVideoLink(testFailureHolder.recordingPath));
            logs.appendChild(getVLCPlayerLink());
        }


        return div;
    }

    private A getVLCPlayerLink() {
        A vlcURL = new A();
        vlcURL.setTarget("_blank");
        vlcURL.setStyle("font-style:italic;font-size:small;color:gray;margin-right:0px;margin-left: -15px;font-style: italic;font-size: small;color: gray;");
        vlcURL.setHref("http://get.videolan.org/vlc/2.1.3/win32/vlc-2.1.3-win32.exe");
        vlcURL.appendText("(Get VLC player)");
        return vlcURL;
    }

    private A getFailureVideoLink(String path) {

        A link = new A();
        link.setCSSClass("i-video-anch");
        link.setTarget("_blank");
        link.setHref(path);
        link.appendText("Recording");
        return link;
    }

    private A getFailureImage(String path) {
        A imageLink = new A();
        imageLink.setHref(path);
        imageLink.setTarget("_blank");
        Img imgs = new Img("", "");
        imgs.setSrc(path);
        imgs.setStyle("max-width: 100%; max-height: 100%;padding-bottom: 30px;");
        return imageLink.appendChild(imgs);
    }

    private A getFailureBrowserConsoleLink(String path) {
        A link = new A();
        link.setTarget("_blank");
        link.setHref(path);
        link.appendText("Browser log");
        return link;
    }

    private A getFailureHtmlSource(String path) {
        A link = new A();
        link.setTarget("_blank");
        link.setHref(path);
        link.appendText("HTML Source");
        return link;
    }

    private Span getFailureHeaderText(String failure) {
        Span toRet = new Span();
        if (failure != null) {
            toRet.appendText("<br><b>Exception Occur: </b>" + failure
                    .replace("<", "&lt;")
                    .replace(">", "&gt;") + "<br><br>");
        }
        return toRet;
    }

    private Span getFailureStackTraceText(Failure failure) {
        Span toRet = new Span();
        toRet.appendText("<b>Stack Trace:</b>" + prettifyExceptionHTML(failure.getException()) + "<br><br>");
        return toRet;
    }

    private Span getFailureCause(final Throwable cause) {
        Span failureCause = new Span();
        if (cause != null && cause.getMessage() != null) {
            failureCause.appendText("<b>Caused by: </b>" + cause
                    .getMessage()
                    .replace("<", "&lt;")
                    .replace(">", "&gt;") + "<br><br>");
        }
        return failureCause;
    }

    private String prettifyExceptionHTML(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        String stackTrace = sw.toString();
        return "<br>" + stackTrace.replace(System.getProperty("line.separator"), "<br/>\n").replace("\tat", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;at");
    }

    private Div htmlGetShowHideSteps() {
        Div main = new Div();
        main.setCSSClass("show-hide-steps");

        Button btn1 = new Button();
        btn1.setAttribute("onclick", "expandCollapseAll(this)");
        btn1.setAttribute("data-isexpanded", "false");
        btn1.appendText("Expand/Collapse All");

        main.appendChild(btn1);

        Button btn = new Button();
        btn.setAttribute("onclick", "showHideProperties(this)");
        btn.setAttribute("data-isexpanded", "false");
        btn.appendText("Show/Hide Steps");
        main.appendChild(btn);

        main.appendChild(this.getShowHideStep("functional", true));
        main.appendChild(this.getShowHideStep("test", true));
        main.appendChild(this.getShowHideStep("info", true));
        main.appendChild(this.getShowHideStep("warning", true));
        main.appendChild(this.getShowHideStep("error", true));
        main.appendChild(this.getShowHideStep("debug", true));

        return main;
    }

    private Span getShowHideStep(String type, Boolean isChecked) {


        String checkBoxId = CssProperties.CHECKBOX.checkBoxCounter();
        Span span = new Span();
        span.setStyle("display: none;");
        span.setCSSClass("step-checkbox");

        Input input = new Input();
        span.appendChild(input);
        input.setTitle("Show/hide " + type + " steps");

        input.setId(checkBoxId);
        input.setAttribute("onclick", "cbShowSteps(this)");
        input.setType("checkbox");
        input.setName(type + "-step");
        if (isChecked)
            input.setChecked("");

        Label label = new Label();
        span.appendChild(label);
        label.setTitle("Show/hide " + type + " steps");
        label.setCSSClass(type + "-step-img step-img");
        label.setFor(checkBoxId);

        return span;

    }

    private Table htmlGetStepsTable(java.util.List<ReportMessage> messageList) {

        Table stepsTable = new Table();
        stepsTable.setCSSClass("steps-table");
        Tbody tbody = new Tbody();
        stepsTable.appendChild(tbody);
        for (ReportMessage rm : messageList) {
            tbody.appendChild(rm.getHtml());
        }
        return stepsTable;
    }

    private String getHour(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(CssProperties.HOUR_FORMAT.value());
        if (date != null)
            return dateFormat.format(date);
        return "";
    }
}

package com.ui.automation.reporter.impl;

import com.hp.gagawa.java.elements.*;
import com.mqm.automation.ui.services.execution.ApplicationContextHolder;
import com.ui.automation.common.element.config.TestProperties;
import com.ui.automation.reporter.api.TestStatus;

import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: coheney
 * Date: 11/03/14
 * Time: 16:15
 * To change this template use File | Settings | File Templates.
 */
@org.springframework.stereotype.Component
public abstract class HtmlGenerator {

    protected Html htmlFile;
    protected Body body;
    protected Div sideBar;
    protected Div mainContentHolder;
    protected Ol treeListHolder;
    protected Div summaryHeader;
    private Div footer;

    private String AUTO_OPEN_REPORT_PROPERTY = "reportAutoOpen";
//    private Boolean AUTO_OPEN_REPORT_VALUE = true;
    // public String CREATE_MAIN_REPORT_PROPERTY = "reportCreateMain";
    // public  Boolean CREATE_MAIN_REPORT_VALUE = false;
    private String TEST_REPORT_DIR = "reports";
    private String RELATIVE_PATH_TO_RESOURCES = "/com/hp/maas/platform/ui/test/report/htmlReportSources";
    private final String jquerySource = "http://code.jquery.com/jquery-2.1.0.min.js";
    private final String faviconBase64 = "data:image/png;base64,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";
    private final String cssFileName = "style.css";
    private final String scriptFileName = "scripts.js";


    public void initHtml(String pageTitle) {
        this.htmlFile = new Html();
        this.initHtmlHead(pageTitle);
        body = new Body();
        this.htmlFile.appendChild(body);
        initMainPanels();
        setSideBar();
        htmlGetFooter();
    }

    protected TestProperties getTestProperties() {
        return ApplicationContextHolder.getApplicationContext().getBean(TestProperties.class);
    }

    public void htmlGetFooter() {
        return;
    }

    public void setSideBar() {
        return;
    }

    private void initMainPanels() {
        this.initHtmlHeader();

        Div mainContainer = new Div();
        mainContainer.setId("container");
        body.appendChild(mainContainer);

        Div pageContent = new Div();
        pageContent.setId("content");
        mainContainer.appendChild(pageContent);
        summaryHeader = new Div();
        summaryHeader.setCSSClass("class-summary");
        pageContent.appendChild(summaryHeader);


        Div panels = new Div();
        panels.setCSSClass("col-panels");
        pageContent.appendChild(panels);

        sideBar = new Div();
        panels.appendChild(sideBar);

        mainContentHolder = new Div();
        panels.appendChild(mainContentHolder);

        Div footerContainer = new Div();
        footerContainer.setId("footer_container");
        footer = new Div();
        footer.setId("footer");
        footerContainer.appendChild(footer);
        this.body.appendChild(footerContainer);

    }

    public void addElementToFooter(Div footerElment) {
        this.footer.appendChild(footerElment);
    }


    public Tbody getTestsClassTBody() {
        Tbody tbody = new Tbody();
        Tr tableHead = new Tr();
        Th cell1 = new Th();
        cell1.appendText("Test");
        tableHead.appendChild(cell1);
        Th cell2 = new Th();
        cell2.appendText("Failure");
        tableHead.appendChild(cell2);
        Th cell3 = new Th();
        cell3.appendText("Suggested Cause");
        tableHead.appendChild(cell3);
        Th cell4 = new Th();
        cell4.appendText("Total Time(Sec)");
        tableHead.appendChild(cell4);
        tbody.appendChild(tableHead);
        return tbody;
    }

    public Div getTestListTableHolder(Div testClassSummary, Table tbl, Integer contentID) {
        Div mainFlow = new Div();
        mainFlow.setCSSClass("flow-content tests-list");
        mainFlow.setId("content-" + contentID);
        mainFlow.setStyle("display: block;");
        Span spId = new Span();
        spId.setStyle("float: right;");
        spId.appendText("ID: " + contentID);
        mainFlow.appendChild(spId);
        H2 header = new H2();
        header.setCSSClass("content-header");
        if (testClassSummary != null) {
            mainFlow.appendChild(testClassSummary);
        }
        if(tbl!=null)    {
            header.appendText("List of Tests in the Class:");
            mainFlow.appendChild(header);
            mainFlow.appendChild(tbl);
            tbl.setCSSClass("test-table");
        }
        return mainFlow;
    }

    public Div getTestListTableHolder(Table tbl, Integer contentID) {
        return getTestListTableHolder(null, tbl, contentID);
    }

    public void initHtmlHeader() {
        Div headerDiv = new Div();
        headerDiv.setId("header_container");
        Div insideHeader = new Div();
        insideHeader.setId("header");
        insideHeader.appendText(CssProperties.TITLE.value());
        headerDiv.appendChild(insideHeader);
        body.appendChild(headerDiv);
    }

    public synchronized void saveFile(String fileName) {
        Path targetDir = getReportsTarget();
        // String fileName = this.testClass.browser + "-" + this.testClass.className + "-" + getTimestamp();
        String filePath = targetDir.toString() + "/" + fileName + ".html";

        Path targetFile = Paths.get(targetDir.toString(), fileName);

        File file = new File(filePath);
        try {
            Files.createDirectories(targetDir);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = null;
            fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(htmlFile.write());
            bw.close();
            System.out.println("HTML Report File saved to: " + filePath);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        Boolean autoOpenReport = getTestProperties().getIsAutoOpenReport();
        if (autoOpenReport) {
            try {
                Desktop.getDesktop().open(file);
            } catch (Exception ex) {
                System.out.println("Unable to open report in browser!");
                ex.printStackTrace();
            }
        }
    }

    private URL codeLocationFromPath(String filePath) {
        try {
            return new File(filePath).toURI().toURL();
        } catch (Exception e) {
            throw new RuntimeException("InvalidCodeLocation " + filePath, e);
        }
    }

    private Path getReportsTarget() {
        final URL targetUrl = codeLocationFromPath("target");
        Path targetPath;
        try {
            targetPath = Paths.get(targetUrl.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException("Failed to get code location from targetPath", e);
        }
        return Paths.get(targetPath.toAbsolutePath().toString(), TEST_REPORT_DIR);
    }


    public String getFileContentFromResources(String fileName) {
        final Path filePath;

        try (InputStream in = this.getClass().getResourceAsStream(RELATIVE_PATH_TO_RESOURCES + "/" + fileName)) {
            return getStringFromInputStream(in);
        } catch (IOException e) {
            throw new RuntimeException("Failed to get file " + fileName, e);
        }
    }

    private String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }

    public void initHtmlHead(String titleStr) {

        Head head = new Head();


        head.appendText("<meta charset=\"UTF-8\">");

        Title title = new Title();
        Link link = new Link();
        link.setAttribute("rel", "icon");
        link.setAttribute("type", "image/png");
        link.setAttribute("href", faviconBase64);
        head.appendChild(link);

        // set jquery link
        Script scriptJQ = new Script("");
        scriptJQ.setAttribute("src", jquerySource);
        head.appendChild(scriptJQ);

        //set functions in head
        Script pageFunctions = new Script("");
        pageFunctions.setAttribute("type", "text/javascript");
        pageFunctions.appendText(getFileContentFromResources(this.scriptFileName));
        head.appendChild(pageFunctions);

        // set style in head
        Style style = new Style("");
        style.setAttribute("type", "text/css");
        style.appendText(getFileContentFromResources(this.cssFileName));
        head.appendChild(style);

        title.appendText(titleStr);
        head.appendChild(title);

        htmlFile.appendChild(head);

    }

    public Span htmlGetBrowserSpan(String browser) {
        Span sp = new Span();

        if (browser.equals("chrome")) {
            sp.setCSSClass("browser-container chrome-img");
            sp.setTitle("Chrome");
        } else if (browser.equals("firefox")) {
            sp.setCSSClass("browser-container firefox-img");
            sp.setTitle("Firefox");
        } else if (browser.equals("iexplorer")) {
            sp.setCSSClass("browser-container ie-img");
            sp.setTitle("Internet Explorer");
        }
        return sp;
    }


    public Div getClassSummary(TestClassAggregator testClass) {
        Div classSummary = new Div();
        if(testClass.isClassIgnored){
            classSummary.appendText("<div><b>Class Ignored!!</b></div>");
            String igValue = testClass.classIgnoreValue.trim().equals("")?"No Reason":  testClass.classIgnoreValue.trim();
            classSummary.appendText("<div><b>Ignored reason: </b>" + igValue + "</div>");
        }
        classSummary.appendChild(htmlSetTestClassNameInClassSummary(testClass));
        htmlSetTestStatusInClassSummary(classSummary, testClass);
        classSummary.appendChild(htmlSetTestsStatussInClassSummary(testClass));
        classSummary.appendChild(htmlSetTestDocInClassSummary(testClass));
        classSummary.appendChild(htmlSetTestTimesInClassSummary(testClass));
        classSummary.appendChild(htmlSetTestClassSummaryHeader(testClass.classHeaderVariables));


        return classSummary;
    }

    private Div htmlSetTestClassSummaryHeader(java.util.Map<String,String> iMap){
        Div toRet = new Div();
        for (Map.Entry<String, String> entry : iMap.entrySet()) {
            Div div = new Div();
            div.appendText("<b>" + entry.getKey() + ":</b> " + entry.getValue());
            toRet.appendChild(div);
        }
        return toRet;
    }


    private Div htmlSetTestsStatussInClassSummary(TestClassAggregator testClass) {

        Integer pass = 0;
        Integer fail = 0;
        Integer ignored = 0;
        Integer assumption_failure = 0;
        Integer unstable_succeed = 0;
        Integer unstable_failed = 0;
        for (TestMethodDetails tmd : testClass.tests) {
            switch (tmd.status) {
                case FAILED:
                    fail++;
                    break;
                case IGNORED:
                    ignored++;
                    break;
                case SUCCEED:
                    pass++;
                    break;
                case ASSUMPTION_FAILURE:
                    assumption_failure++;
                    break;
                case UNSTABLE_FAILED:
                    unstable_failed++;
                    break;
                case UNSTABLE_SUCCEED:
                    unstable_succeed++;
                    break;
            }
        }

        Div nameMain = new Div();

        nameMain.setAttribute("title", "Indication of all test that run in the class");
        B header = new B();
        header.appendText("Tests Result(" + testClass.tests.size() + "): ");
        nameMain.appendChild(header);

        if (testClass.tests.size() == 0) {
            Span sp = new Span();
            sp.appendText("No Tests Run");
            nameMain.appendChild(sp);
            return nameMain;
        }

        for (TestStatus ts : TestStatus.values()) {
            if (ts.equals(TestStatus.FAILED)) {
                Span sp = new Span();
                sp.setCSSClass(CssProperties.IMG_FAIL.value() + " test-status-result");
                sp.setAttribute("title", " " + fail + " Test failed!");
                sp.appendText(fail.toString());
                nameMain.appendChild(sp);
            } else if (ts.equals(TestStatus.SUCCEED)) {
                Span sp = new Span();
                sp.setCSSClass(CssProperties.IMG_SUCCESS.value() + " test-status-result");
                sp.setAttribute("title", " " + pass + " Test Succeed!");
                sp.appendText(pass.toString());
                nameMain.appendChild(sp);
            } else if (ts.equals(TestStatus.IGNORED)) {
                Span sp = new Span();
                sp.setCSSClass(CssProperties.IMG_IGNORE.value() + " test-status-result");
                sp.setAttribute("title", " " + ignored + " Test Ignored!");
                sp.appendText(ignored.toString());
                nameMain.appendChild(sp);
            } else if (ts.equals(TestStatus.ASSUMPTION_FAILURE)) {
                Span sp = new Span();
                sp.setCSSClass(CssProperties.IMG_ASS_FAIL.value() + " test-status-result");
                sp.setAttribute("title", " " + assumption_failure + " Test Assuming Failure!");
                sp.appendText(assumption_failure.toString());
                nameMain.appendChild(sp);
            }else if (ts.equals(TestStatus.UNSTABLE_FAILED)) {
                Span sp = new Span();
                sp.setCSSClass(CssProperties.IMG_UNSTABLE_FAIL.value() + " test-status-result");
                sp.setAttribute("title", " " + unstable_failed + " Unstable Test Failure!");
                sp.appendText(unstable_failed.toString());
                nameMain.appendChild(sp);
            } else if (ts.equals(TestStatus.UNSTABLE_SUCCEED)) {
                Span sp = new Span();
                sp.setCSSClass(CssProperties.IMG_UNSTABLE_SUCCEED.value() + " test-status-result");
                sp.setAttribute("title", " " + unstable_succeed + " Unstable Test Succeed!");
                sp.appendText(unstable_succeed.toString());
                nameMain.appendChild(sp);
            }
        }

        return nameMain;
    }

    private void htmlSetTestStatusInClassSummary(Div classSummary, TestClassAggregator testClass) {
        Div nameMain = new Div();
        B header = new B();
        header.appendText("Test Class Failure: ");
        nameMain.appendChild(header);
        Span text = new Span();
        text.setCSSClass(CssProperties.IMG_FAIL.value());
        text.setStyle("padding-left:20px;color:red");
        nameMain.appendChild(text);
        if ((testClass.beforeClass != null && testClass.afterClass != null) &&
                (testClass.beforeClass.failures.size() > 0 && testClass.afterClass.failures.size() > 0)) {
            text.appendText(" Error Occur in Before Class and in After Class!");
            classSummary.appendChild(nameMain);
        } else if (testClass.beforeClass != null && testClass.beforeClass.failures.size() > 0) {
            text.appendText(" Error Occur in Before Class!");
            classSummary.appendChild(nameMain);
        } else if (testClass.afterClass != null && testClass.afterClass.failures.size() > 0) {
            text.appendText(" Error Occur in After Class!");
            classSummary.appendChild(nameMain);
        }

    }

    private Div htmlSetTestTimesInClassSummary(TestClassAggregator testClass) {
        Div nameMain = new Div();

        B header = new B();
        header.appendText("Start Time: ");
        nameMain.appendChild(header);
        Span text = new Span();
        text.appendText(getHour(testClass.classStartTime) + " ");
        nameMain.appendChild(text);

        B header2 = new B();
        header2.appendText(" End Time: ");
        nameMain.appendChild(header2);
        Span text2 = new Span();
        text2.appendText(getHour(testClass.classEndTime) + " ");
        nameMain.appendChild(text2);


        B header3 = new B();
        header3.appendText("Total(Sec): ");
        nameMain.appendChild(header3);
        Span text3 = new Span();
        text3.appendText(String.valueOf(testClass.totalExecutionSecTime));
        nameMain.appendChild(text3);

        return nameMain;
    }

    private Div htmlSetTestDocInClassSummary(TestClassAggregator testClass) {
        Div testName = new Div();
        if (!testClass.classTestDocName.equals("")) {
            B testHeader = new B();
            testHeader.appendText("Test Name: ");
            testName.appendChild(testHeader);
            Span testText = new Span();
            testText.appendText(testClass.classTestDocName);
            testName.appendChild(testText);
        }
        if (!testClass.classTestDocDescription.equals("")) {
            testName.appendChild(new Br());
            B testHeader = new B();
            testHeader.appendText("Test Description: ");
            testName.appendChild(testHeader);
            Span testText = new Span();
            testText.appendText(testClass.classTestDocDescription);
            testName.appendChild(testText);
        }
        return testName;
    }

    private Div htmlSetTestClassNameInClassSummary(TestClassAggregator testClass) {
        Div testName = new Div();

        testName.setStyle("font-size:20px");
        B testHeader = new B();
        testHeader.appendText("Test Class Name: ");
        testName.appendChild(testHeader);
        Span testText = new Span();
        testText.appendText(testClass.className + " ");
        testName.appendChild(testText);
        if (!testClass.classCategory.equals("")) {
            Span categoryText = new Span();
            categoryText.appendText(" (Category: " + testClass.classCategory + ")");
            testName.appendChild(categoryText);
        }
        testName.appendChild(htmlGetBrowserSpan(testClass.browser));

        return testName;

    }

    public String getHour(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(CssProperties.HOUR_FORMAT.value());
        if (date != null)
            return dateFormat.format(date);
        return "";
    }
}

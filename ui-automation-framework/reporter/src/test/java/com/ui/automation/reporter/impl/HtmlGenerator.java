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

    //private String AUTO_OPEN_REPORT_PROPERTY = "reportAutoOpen";
    //private Boolean AUTO_OPEN_REPORT_VALUE = true;
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

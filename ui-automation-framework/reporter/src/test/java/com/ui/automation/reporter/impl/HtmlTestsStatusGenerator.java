package com.ui.automation.reporter.impl;

import com.hp.gagawa.java.elements.*;
import com.ui.automation.reporter.api.TestStatus;
import org.springframework.stereotype.Component;

import java.lang.Object;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: coheney
 * Date: 11/03/14
 * Time: 15:25
 * To change this template use File | Settings | File Templates.
 */
@Component
public class HtmlTestsStatusGenerator extends HtmlGenerator {
    public Boolean isStarted = false;
    Integer contentId = 1;
    private Div classSummaryHeader = new Div();
    private Tbody mainTestTable;
    private List<String> tempClassList = new ArrayList<>();


    public void addClass(TestClassAggregator testClass) {

        tempClassList.add(testClass.className);
        synchronized (new Object()) {
            if (!isStarted) {

                initHtml("Tests Report");

                mainContentHolder.setId("main-contentmain");
                setPageSummary(testClass.classCategory);
                setHelpContent();
                isStarted = true;
            }
        }

        initMainContent(testClass);


        saveFile("test-results");

    }

    private void setIgnoredClass(TestClassAggregator testClass) {
        // set table row
        contentId++;
        Tr tr1 = new Tr();
        Td className = new Td();
        Td browser = new Td();
        Td classRunTime = new Td();
        Td testName = new Td();
        Td testRunTime = new Td();
        tr1.appendChild(className);
        tr1.appendChild(browser);
        tr1.appendChild(classRunTime);
        tr1.appendChild(testName);
        tr1.appendChild(testRunTime);
        className.setAttribute("data-cont", "content-" + contentId);
        className.setAttribute("onclick", "selectview(this)");
        className.setCSSClass(CssProperties.HAND_CURSOR.value());
        browser.setWidth("60px");
        classRunTime.setWidth("100px");
        classRunTime.appendText("-");
        Div name = new Div();
        name.appendText(testClass.className);
        name.setCSSClass(CssProperties.ELLIPSIS_CLASS.value() + " " + CssProperties.IMG_IGNORE.value() + " " + " img-text");
        className.appendChild(name);
        browser.appendText("-");
        testName.appendText("-");
        testRunTime.appendText("-");
        mainTestTable.appendChild(tr1);

        // set the main class tree node
        Li mainForClass = new Li();
        mainForClass.setCSSClass("node-ignored");
        A aLink = new A();
        aLink.setAttribute("onclick", "selectview(this)");
        aLink.setAttribute("data-cont", "content-" + contentId);
        aLink.setCSSClass(CssProperties.ELLIPSIS_CLASS.value() + " " + CssProperties.IMG_IGNORE.value());
        aLink.appendText(testClass.className);
        mainForClass.appendChild(aLink);
        Ol testsTreeNodesHolder = new Ol();
        mainForClass.appendChild(testsTreeNodesHolder);
        this.treeListHolder.appendChild(mainForClass);
        aLink.setTitle("Class is Ignored!");

        //set content

        Div classSummary = new Div();
        classSummary.setCSSClass("class-summary");
        classSummary.setStyle("margin-right:40px");
        classSummary.appendChild(this.getClassSummary(testClass));
        Div testsListPerClassHolder = getTestListTableHolder(classSummary, null, contentId);
        testsListPerClassHolder.setStyle("display: none;");
        mainContentHolder.appendChild(testsListPerClassHolder);
    }

    private synchronized void initMainContent(TestClassAggregator testClass) {

        if (testClass.isClassIgnored) {
            setIgnoredClass(testClass);
            return;
        }

        contentId++;
        Integer contentIDforClass = this.contentId;
        contentId++;
        Integer contentIdForBeforeClass = this.contentId;
        contentId++;
        Table classTestTbl = new Table();
        Tbody tbody = getTestsClassTBody();
        classTestTbl.appendChild(tbody);
        Div classSummary = new Div();
        classSummary.setCSSClass("class-summary");
        classSummary.setStyle("margin-right:40px");
        classSummary.appendChild(this.getClassSummary(testClass));
        Div testsListPerClassHolder = getTestListTableHolder(classSummary, classTestTbl, contentIDforClass);
        testsListPerClassHolder.setStyle("display: none;");

        mainContentHolder.appendChild(testsListPerClassHolder);


        Integer tFailed = 0;
        Integer tScceed = 0;
        Integer tIgnored = 0;
        Integer tUnstableFailed = 0;
        Integer tUnstableSucceed = 0;

        // set test row of first main table list
        Tr tr1 = new Tr();
        Integer rowSpan = testClass.tests.size() + 2;
        Td className = new Td();
        Td browser = new Td();
        Td classRunTime = new Td();
        Td testName = new Td();
        Td testRunTime = new Td();

        tr1.appendChild(className);
        tr1.appendChild(browser);
        tr1.appendChild(classRunTime);
        tr1.appendChild(testName);
        tr1.appendChild(testRunTime);

        className.setRowspan(rowSpan.toString());
        className.setAttribute("data-cont", "content-" + contentIDforClass);
        className.setAttribute("onclick", "selectview(this)");
        className.setCSSClass(CssProperties.HAND_CURSOR.value());
        browser.setRowspan(rowSpan.toString());
        browser.setWidth("60px");
        classRunTime.setRowspan(rowSpan.toString());
        classRunTime.setWidth("100px");
        classRunTime.appendText(String.valueOf(testClass.totalExecutionSecTime));
        Div name = new Div();
        name.appendText(testClass.className);
        name.setCSSClass(CssProperties.ELLIPSIS_CLASS.value());
        className.appendChild(name);

        browser.appendChild(htmlGetBrowserSpan(testClass.browser));
        testName.appendChild(getTestNameContent(testClass.beforeClass, contentIdForBeforeClass));
        testName.setAttribute("data-cont", "content-" + contentIdForBeforeClass);
        testName.setAttribute("onclick", "selectview(this)");
        testRunTime.appendText(String.valueOf(testClass.beforeClass.totalExecutionSecTime));
        tbody.appendChild(testClass.beforeClass.getHtmlTestListRow(contentIdForBeforeClass.toString()));

        mainTestTable.appendChild(tr1);

        // set the main class tree node
        Li mainForClass = new Li();

        A aLink = new A();
        aLink.setAttribute("onclick", "selectview(this)");
        aLink.setAttribute("data-cont", "content-" + contentIDforClass);
        aLink.setCSSClass(CssProperties.ELLIPSIS_CLASS.value() + " " + CssProperties.IMG_SUCCESS.value());

        // String mainNodeClass = "class-succeed";
        aLink.appendText(testClass.className);

        mainForClass.appendChild(aLink);

        Ol testsTreeNodesHolder = new Ol();
        mainForClass.appendChild(testsTreeNodesHolder);
        this.treeListHolder.appendChild(mainForClass);

        // do for before class
        Li beforeClassTreeNode = testClass.beforeClass.getHtmlTreeNode(contentIdForBeforeClass.toString());
        beforeClassTreeNode.setCSSClass(this.getClassNodeOfTreeLI(testClass.beforeClass.status));
        testsTreeNodesHolder.appendChild(beforeClassTreeNode);
        mainContentHolder.appendChild(testClass.beforeClass.getHtmlFlowContent(contentIdForBeforeClass.toString()));
        //do for all tests
        for (TestMethodDetails tmd : testClass.tests) {
            contentId++;
            switch (tmd.status) {
                case IGNORED:
                    tIgnored++;
                    break;
                case FAILED:
                    tFailed++;
                    break;
                case UNSTABLE_FAILED:
                    tUnstableFailed++;
                    break;
                case UNSTABLE_SUCCEED:
                    tUnstableSucceed++;
                case SUCCEED:
                    tScceed++;
                    break;
            }

            //sort tests by failed status
//            Collection<TestMethodDetails> tests = testClass.tests;
//            List<TestMethodDetails> list = new ArrayList<TestMethodDetails>(tests);
//            Collections.sort(list, new SortTestsList());


            //set tree node in class

            Li li = tmd.getHtmlTreeNode(contentId.toString());
            li.setCSSClass(this.getClassNodeOfTreeLI(tmd.status));
            testsTreeNodesHolder.appendChild(li);

            // add the row to main table
            Tr tri = new Tr();
            Td testNamei = new Td();
            Td testRunTimei = new Td();
            tri.appendChild(testNamei);
            tri.appendChild(testRunTimei);
            testNamei.appendChild(getTestNameContent(tmd, contentId));
            testRunTimei.appendText(String.valueOf(tmd.totalExecutionSecTime));
            mainTestTable.appendChild(tri);

            // set tree node title
            aLink.setTitle(TestStatus.FAILED + ": " + tFailed + "\n" +
                    TestStatus.IGNORED + ": " + tIgnored + "\n" +
                    TestStatus.SUCCEED + ": " + tScceed + "\n" +
                    TestStatus.UNSTABLE_SUCCEED + ": " + tUnstableSucceed + "\n" +
                    TestStatus.UNSTABLE_FAILED + ": " + tUnstableFailed + "\n" +
                    "Before Class: " + testClass.beforeClass.status + "\n" +
                    "After Class: " + testClass.afterClass.status);
            tbody.appendChild(tmd.getHtmlTestListRow(contentId.toString()));
            mainContentHolder.appendChild(tmd.getHtmlFlowContent(contentId.toString()));


        }
        //if class is "node-succed" or "node-failed" it's means that the class will display according to the filter (if I check the success tests or failed test in the report filter)
        //The images (IMG_FAIL or IMG_UNSTABLE_FAIL or IMG_SUCCESS) will determine the image near to the class in the report
        if (tFailed > 0 || testClass.beforeClass.status.equals(TestStatus.FAILED)) {
            // Test failed before class ran or during a single test method run
            mainForClass.setCSSClass("node-failed");
            aLink.setCSSClass(CssProperties.ELLIPSIS_CLASS.value() + " " + CssProperties.IMG_FAIL.value());
        } else if (tUnstableFailed > 0  || testClass.beforeClass.status.equals(TestStatus.UNSTABLE_FAILED)) {
            mainForClass.setCSSClass("node-unstable-failed");
            aLink.setCSSClass(CssProperties.ELLIPSIS_CLASS.value() + " " + CssProperties.IMG_UNSTABLE_FAIL.value());
        } else {
            mainForClass.setCSSClass("node-succeed");
        }

        // after class - add to tree node
        contentId++;
        Li afterClassTreeNode = testClass.afterClass.getHtmlTreeNode(contentId.toString());
        afterClassTreeNode.setCSSClass(this.getClassNodeOfTreeLI(testClass.afterClass.status));
        testsTreeNodesHolder.appendChild(afterClassTreeNode);
        // after class add to table row
        Tr tri = new Tr();
        Td testNamei = new Td();
        Td testRunTimei = new Td();
        tri.appendChild(testNamei);
        tri.appendChild(testRunTimei);
        testNamei.appendChild(getTestNameContent(testClass.afterClass, contentId));
        testRunTimei.appendText(String.valueOf(testClass.afterClass.totalExecutionSecTime));
        tbody.appendChild(testClass.afterClass.getHtmlTestListRow(contentIdForBeforeClass.toString()));
        mainTestTable.appendChild(tri);
        mainContentHolder.appendChild(testClass.afterClass.getHtmlFlowContent(contentId.toString()));

    }

    private void setHelpContent() {
        Div helpDiv = new Div();
        helpDiv.setCSSClass("flow-content");
        helpDiv.setStyle("display: block;");
        helpDiv.setId("content-88888");
        H2 header = new H2();
        header.setCSSClass("content-header");
        header.appendText("Help");
        helpDiv.appendChild(header);
        Div summ = new Div();
        summ.appendText("This HTML report summarize all tests in category.<br/>" +
                "In the right hand side you can find tree-list of the tests. Main node is the class name and the childes are the test method inside.<br/>" +
                "To view the result of any test method or class just click on the node in the tree and you will see the content of it.<br/>" +
                "<p><b>Content ID:</b> in the top-right corner you will see <b>ID:X</b>, this can be used in the URL as query to go to specific test<br/>" +
                "For Example: if you want to send to someone the exact test you want him to check you need to send him: <i><u>http://(url to the page>)<b>?id=5</b></u></i></p>" +
                "<p><b>Main Icons Legend:</b><br/>" +
                "<span class=\"success-img img-text\">Indicate test <b>succeed</b></span><br/>" +
                "<span class=\"ignore-img img-text\">Indicate test <b>ignored</b></span><br/>" +
                "<span class=\"fail-img img-text\">Indicate test <b>failed</b></span><br/>" +
                "<span class=\"assfail-img img-text\">Indicate test <b>was failed on condition (e.g. @UnstableTest)</b></span><br/>" +
                "<span class=\"assfail-fail-img img-text\">Indicate <b>@UnstableTest that run and failed</b></span><br/>" +
                "<span class=\"assfail-succeed-img img-text\">Indicate <b>@UnstableTest that run and succeed</b></span><br/>" +

                "<span class=\"functional-step-img img-text\">Automatic Functional Step - information about the action that generated automatically</span><br/>" +
                "<span class=\"test-step-img img-text\">Test Step - step that indicate about test information </span><br/>" +
                "</p>");

        helpDiv.appendChild(summ);


        mainContentHolder.appendChild(helpDiv);
    }

    private String getClassNodeOfTreeLI(TestStatus status) {
        switch (status) {
            case FAILED:
                classSummaryHeader.setCSSClass("fail-img img-text failed-test ");
                return "node-failed";
            case IGNORED:
                return "node-ignored";
            case ASSUMPTION_FAILURE:
                return "node-assumption-failure";
            case SUCCEED:
                return "node-succeed";
            case UNSTABLE_FAILED:
                return "node-unstable-failed";
            case UNSTABLE_SUCCEED:
                return "node-unstable-succeed";
            default:
                return "node-succeed";
        }
    }

    private Div getTestNameContent(TestMethodDetails test, Integer contentID) {

        Div div = new Div();
        div.setAttribute("data-cont", "content-" + contentID.toString());
        div.setAttribute("onclick", "selectview(this)");
        String iCssClass = "";
        switch (test.status) {
            case FAILED:
                iCssClass = "failed-test " + CssProperties.IMG_FAIL.value();
                break;
            case SUCCEED:
                iCssClass = CssProperties.IMG_SUCCESS.value();
                break;
            case IGNORED:
                iCssClass = CssProperties.IMG_IGNORE.value();
                break;
            case UNSTABLE_SUCCEED:
                iCssClass = CssProperties.IMG_UNSTABLE_SUCCEED.value();
                break;
            case UNSTABLE_FAILED:
                iCssClass = CssProperties.IMG_UNSTABLE_FAIL.value();
                break;
            case ASSUMPTION_FAILURE:
                iCssClass = CssProperties.IMG_ASS_FAIL.value();
                break;
        }
        div.setCSSClass(iCssClass + " img-text " + CssProperties.ELLIPSIS_CLASS.value() + " " + CssProperties.HAND_CURSOR.value());
        div.appendText(test.name);

        return div;
    }


    @Override
    public void setSideBar() {
        sideBar.setId("sidebar-main");
        H1 treeHeader = new H1();
        treeHeader.appendText("Tests:");
        sideBar.appendChild(treeHeader);
        sideBar.appendChild(showHideNodesCheckbox(CssProperties.IMG_FAIL.value(), "Failed", true));
        sideBar.appendChild(showHideNodesCheckbox(CssProperties.IMG_IGNORE.value(), "Ignored", false));
        sideBar.appendChild(showHideNodesCheckbox(CssProperties.IMG_SUCCESS.value(), "Succeed", false));
        sideBar.appendChild(showHideNodesCheckbox(CssProperties.IMG_ASS_FAIL.value(), "Assumption-Failure", true));
        sideBar.appendChild(showHideNodesCheckbox(CssProperties.IMG_UNSTABLE_FAIL.value(), "Unstable-Failed", true));
        sideBar.appendChild(showHideNodesCheckbox(CssProperties.IMG_UNSTABLE_SUCCEED.value(), "Unstable-Succeed", false));


        treeListHolder = new Ol();
        treeListHolder.setCSSClass("rounded-list");
        sideBar.appendChild(treeListHolder);
    }

    @Override
    public void htmlGetFooter() {

        Div footerHolder = new Div();
        Span helpImage = new Span();
        helpImage.appendText("  help");
        helpImage.setCSSClass(CssProperties.IMG_HELP.value() + " " + CssProperties.HAND_CURSOR.value() + " img-text");
        helpImage.setAttribute("data-cont", "content-88888");
        helpImage.setAttribute("onclick", "selectview(this)");
        footerHolder.appendChild(helpImage);
        addElementToFooter(footerHolder);

    }

    public void setPageSummary(String category) {

        classSummaryHeader = new Div();
        classSummaryHeader.setStyle("font-size:22px;margin-bottom:10px;");
        classSummaryHeader.setCSSClass("success-img img-text succeed-test");
        classSummaryHeader.appendText("Tests Category: " + category);
        summaryHeader.appendChild(classSummaryHeader);
        Div tableContainer = new Div();
        summaryHeader.appendChild(tableContainer);
        Span tableHeader = new Span();
        tableContainer.appendChild(tableHeader);
        tableHeader.appendText("Tests Result Table:");
        tableHeader.setTitle("font-weight: bold;");
        tableHeader.setCSSClass("expand-img");
        tableHeader.setAttribute("onclick", "expColTestResultTable(this)");

        Table testSummaryTable = new Table();
        tableContainer.appendChild(testSummaryTable);
        testSummaryTable.setCSSClass("test-summary-table");

        this.mainTestTable = new Tbody();
        testSummaryTable.appendChild(this.mainTestTable);
        Tr tableHeaderRow = new Tr();
        mainTestTable.appendChild(tableHeaderRow);
        Th t1 = new Th();
        t1.appendText("Test Class Name");
        tableHeaderRow.appendChild(t1);
        Th t2 = new Th();
        t2.appendText("Browser");
        tableHeaderRow.appendChild(t2);
        Th t3 = new Th();
        t3.appendText("Run Time(Sec)");
        tableHeaderRow.appendChild(t3);
        Th t4 = new Th();
        t4.appendText("Test Name");
        tableHeaderRow.appendChild(t4);
        Th t5 = new Th();
        t5.appendText("Run Time(Sec)");
        tableHeaderRow.appendChild(t5);

    }

    private Span showHideNodesCheckbox(String type, String toolTipType, Boolean isChecked) {
        String checkBoxId = CssProperties.CHECKBOX.checkBoxCounter();
        Span span = new Span();

        Input input = new Input();
        span.appendChild(input);
        input.setTitle("Show/hide " + toolTipType + " test");

        input.setId(checkBoxId);
        input.setAttribute("onclick", "showHideNodes(this)");
        input.setType("checkbox");
        input.setName("node-" + toolTipType.toLowerCase());
        if (isChecked)
            input.setChecked("");

        Label label = new Label();
        span.appendChild(label);
        label.setTitle("Show/hide " + toolTipType + " test");
        label.setCSSClass(type + " step-img");
        label.setFor(checkBoxId);

        return span;
    }


}

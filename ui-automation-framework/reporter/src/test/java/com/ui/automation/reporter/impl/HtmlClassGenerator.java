package com.ui.automation.reporter.impl;

import com.hp.gagawa.java.elements.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: coheney
 * Date: 06/03/14
 * Time: 17:23
 * To change this template use File | Settings | File Templates.
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HtmlClassGenerator extends HtmlGenerator {

    @Autowired
    HtmlTestsStatusGenerator mainHtmlReport;

    private TestClassAggregator testClass;


    private Ol treeTestListHolder;
    private Table tableTestListHolder = new Table();
    private Tbody tableOfTestList = new Tbody();


    public void createClassReport(TestClassAggregator testClass) {
        this.testClass = testClass;
        initHtml("Test - " + this.testClass.className);
        initBaseHtml();
        initTestContent();
        saveFile(this.testClass.browser + "-" + this.testClass.className + "-" + getTimestamp());

        Boolean isCreateMainFile = getTestProperties().getIsCreateFullReportHTML();
        if (isCreateMainFile)
            mainHtmlReport.addClass(this.testClass);
    }

    private void initTestContent() {
        initMainClassSummary();
        initClassTestContent();
    }

    Integer contentId = 3;

    private void initClassTestContent() {

        setTestContent(this.testClass.beforeClass, "1", true);
        setTestList();
        for (TestMethodDetails tmd : this.testClass.tests) {
            contentId++;
            setTestContent(tmd, contentId.toString(), false);
        }
        setTestContent(this.testClass.afterClass, "99999", true);
    }


    private void setTestContent(TestMethodDetails test, String contentId, Boolean isBeforeAfter) {
        this.tableOfTestList.appendChild(test.getHtmlTestListRow(contentId));
        mainContentHolder.appendChild(test.getHtmlFlowContent(contentId));
        if (isBeforeAfter) {
            this.treeListHolder.appendChild(test.getHtmlTreeNode(contentId));
        } else {
            this.treeTestListHolder.appendChild(test.getHtmlTreeNode(contentId));
        }
    }


    /**
     * Will set the test list element Ol
     */
    private void setTestList() {
        Li treeTestsNode = new Li();
        treeListHolder.appendChild(treeTestsNode);

        A aLinkTest = new A();
        treeTestsNode.appendChild(aLinkTest);
        aLinkTest.setAttribute("onclick", "selectview(this)");
        aLinkTest.setAttribute("data-cont", "content-2");
        aLinkTest.setTitle("List of run tests");
        aLinkTest.appendText("Tests List");
        this.treeTestListHolder = new Ol();
        treeTestsNode.appendChild(this.treeTestListHolder);
        mainContentHolder.appendChild(getTestListTableHolder(tableTestListHolder, 2));
    }


    private void initMainClassSummary() {
        Div div = this.getClassSummary(this.testClass);
        this.summaryHeader.appendChild(div);
    }

    private void initBaseHtml() {

        tableOfTestList = this.getTestsClassTBody();
        this.tableTestListHolder.appendChild(tableOfTestList);
        mainContentHolder.setId("main-content");
    }

    @Override
    public void setSideBar() {
        H1 treeHeader = new H1();
        treeHeader.appendText("Class Flow");
        sideBar.appendChild(treeHeader);
        sideBar.setId("sidebar");
        treeListHolder = new Ol();
        treeListHolder.setCSSClass("rounded-list");
        sideBar.appendChild(treeListHolder);
    }


    @Override
    public void htmlGetFooter() {

        Div footerHolder = new Div();

        Span legendTitle = new Span();
        legendTitle.appendText("Legend: ");
        footerHolder.appendChild(legendTitle);

        Span successImage = new Span();
        successImage.appendText("Succeed Test");
        successImage.setCSSClass(CssProperties.IMG_SUCCESS.value());
        footerHolder.appendChild(successImage);

        Span failedImg = new Span();
        failedImg.appendText("Failed Test");
        failedImg.setCSSClass(CssProperties.IMG_FAIL.value());
        footerHolder.appendChild(failedImg);

        Span IgnoreImage = new Span();
        IgnoreImage.appendText("Ignored Test");
        IgnoreImage.setCSSClass(CssProperties.IMG_IGNORE.value());
        footerHolder.appendChild(IgnoreImage);



        Span assFailImage = new Span();
        assFailImage.appendText("Assumption Fail Test");
        assFailImage.setCSSClass(CssProperties.IMG_ASS_FAIL.value());
        footerHolder.appendChild(assFailImage);

        Span unsSucceed = new Span();
        unsSucceed.appendText("@Unstable Test Succeed");
        unsSucceed.setCSSClass(CssProperties.IMG_UNSTABLE_SUCCEED.value());
        footerHolder.appendChild(unsSucceed);

        Span unsFailed = new Span();
        unsFailed.appendText("@Unstable Test Failed");
        unsFailed.setCSSClass(CssProperties.IMG_UNSTABLE_FAIL.value());
        footerHolder.appendChild(unsFailed);

        addElementToFooter(footerHolder);
    }


    private String getTimestamp() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy-HH-mm-ss");
        Date date = new Date();
        return dateFormat.format(date);
    }


}

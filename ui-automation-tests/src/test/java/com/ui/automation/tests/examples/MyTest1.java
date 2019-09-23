package com.ui.automation.tests.examples;

import com.google.gson.GsonBuilder;
import com.ui.automation.elements.entities.Campaign;
import com.ui.automation.tests.BaseAutomationTest;
import com.ui.automation.tests.UI;
import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.springframework.test.context.TestExecutionListeners;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

@TestExecutionListeners(MyTestExecutionListener1.class)
public class MyTest1 extends BaseAutomationTest {

    private Campaign campaign;

    @ClassRule
    public static TestRule classRule = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            System.out.println("classRule.starting");
        }

        @Override
        protected void finished(Description description) {
            System.out.println("classRule.finished");
        }
    };

    @Rule
    public TestRule methodRule = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            System.out.println("methodRule.starting");
        }

        @Override
        protected void finished(Description description) {
            System.out.println("methodRule.finished");
        }

    };

    @Before
    public void beforeMethod() throws FileNotFoundException {
        String path = "/Users/hanan.malka/Developer/UIAutoationFramework/ui-automation-tests/src/test/resources/jsonobjects/campaignjson/Campaign";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        campaign = new GsonBuilder().create().fromJson(bufferedReader, Campaign.class);
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("MyTest1:afterClass");
    }

    @Test
    public void fillCampaignGeneralSettings() {

        UI.sideBarMenu.navigateToPage("Campaigns");
        UI.campaignsPage.clickCreateCampaignButton();

        UI.newCampaignPage.newCampaignForm.generalSettingsGroup.mainPanel.fillPanel(campaign);




        UI.newCampaignPage.newCampaignForm.generalSettingsGroup.fillMainPanel(campaign);



        UI.newCampaignPage.newCampaignForm.generalSettingsGroup.mainPanel.setAdvertiser(campaign.getAdvertiserTitles().getAdvertisers().getName());
        UI.newCampaignPage.newCampaignForm.generalSettingsGroup.mainPanel.setTitle(campaign.getAdvertiserTitles().getDescription());
        UI.newCampaignPage.newCampaignForm.generalSettingsGroup.mainPanel.setBid(campaign.getBids().getRate());
        UI.newCampaignPage.newCampaignForm.generalSettingsGroup.mainPanel.setCostModel(campaign.getCostModel());
    }





}
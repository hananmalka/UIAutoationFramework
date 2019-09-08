package com.ui.automation.tests.examples;

import com.ui.automation.locator.Locator;
import com.ui.automation.tests.BaseAutomationTest;
import com.ui.automation.tests.UI;
import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//@TestExecutionListeners(MyTestExecutionListener1.class)
public class MyTest1 {//extends BaseAutomationTest {

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

    @BeforeClass
    public static void beforeClass() {
        System.out.println("MyTest1:beforeClass");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("MyTest1:afterClass");
    }

    @Test
    public void TestA() throws InterruptedException {


        String username = getProperty("config.properties", "defaultUsername");
        String password = getProperty("config.properties", "defaultPassword");
        UI.loginPage.performLogin(username, password);
        
        UI.sideBarMenu.navigateToPage("Advertisers");

        UI.advertisersPage.advertisersPageHeader.clickAddAdvertiserButton();
        UI.neAdvertiserPage.newAdvertiserForm.generalSettingsPane.nameTextBox().setValue("Hanan");
        UI.neAdvertiserPage.newAdvertiserForm.generalSettingsPane.ownerDropDown().open(Locator.id("dropdown-owners"));
        UI.neAdvertiserPage.newAdvertiserForm.generalSettingsPane.ownerDropDown().selectItem("openx ");

//        UI.campaignsPage.campaignPageHeader.clickCreateCampaignButton();
//        UI.campaignsPage.campaignsContainer.campaignsPageFilter().advertisersFilter().selectItem("Upopa Games");

//        UI.newCampaignPage.newCampaignForm.budgetAndCappingGroup.addLimitation(Limitation.DAILY_BUDGET);
//        UI.newCampaignPage.newCampaignForm.budgetAndCappingGroup.setLimitation(10,10, true);
//        UI.newCampaignPage.newCampaignForm.budgetAndCappingGroup.setAlerts(true);
//        UI.newCampaignPage.newCampaignForm.budgetAndCappingGroup.setMailingList(users);

//        UI.newCampaignPage.newCampaignForm.generalSettingsGroup.mainForm.selectAdvertiser("Tommy (AA3)");
//        UI.newCampaignPage.newCampaignForm.generalSettingsGroup.mainForm.selectTitle("Facebook");
//        UI.newCampaignPage.newCampaignForm.generalSettingsGroup.mainForm.setBid(10);
//        UI.newCampaignPage.newCampaignForm.generalSettingsGroup.mainForm.setCostModel("CPC");


//        UI.newCampaignPage.newCampaignForm.engageNotificationSettingsGroup.engageNotificationSettingsPane.enableEngageNotification();
//        UI.newCampaignPage.newCampaignForm.engageNotificationSettingsGroup.engageNotificationSettingsPane.setMainText("Hanan");




//        Pages.campaignsPage


        System.out.println("MyTest1:TestA");
    }

    @Test
    public void TestB() {
        System.out.println("MyTest1:TestB");
    }


    public static String getProperty(String fileName, String property) {
        String result = "";
        Properties prop = new Properties();
        InputStream input = null;
        try {
            String accountsFilePath = ClassLoader.getSystemResource(fileName).getFile();
            input = new FileInputStream(accountsFilePath);

            // load a properties file
            prop.load(input);

            result = prop.getProperty(property);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
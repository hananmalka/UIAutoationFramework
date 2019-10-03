package com.ui.automation.tests.examples;

import com.ui.automation.elements.entities.Campaign;
import com.ui.automation.tests.BaseAutomationTest;
import com.ui.automation.tests.UI;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.context.TestExecutionListeners;

@TestExecutionListeners(MyTestExecutionListener1.class)
public class MyTest2 extends BaseAutomationTest {

    private static Campaign campaign;

//    @ClassRule
//    public static TestRule classRule = new TestWatcher() {
//        @Override
//        protected void starting(Description description) {
//            System.out.println("classRule.starting");
//        }
//
//        @Override
//        protected void finished(Description description) {
//            System.out.println("classRule.finished");
//        }
//    };
//
//    @Rule
//    public TestRule methodRule = new TestWatcher() {
//        @Override
//        protected void starting(Description description) {
//            System.out.println("methodRule.starting");
//        }
//
//        @Override
//        protected void finished(Description description) {
//            System.out.println("methodRule.finished");
//        }
//
//    };

//    @Rule
//    public TestRule methodRule = new TestWatcher() {
//        @Override
//        protected void starting(Description description) {
//            System.out.println("methodRule.starting");
//        }
//
//        @Override
//        protected void finished(Description description) {
//            System.out.println("methodRule.finished");
//        }
//
//    };

    @BeforeClass
    public static void beforeClass() {
//        String path = "/Users/hanan.malka/Developer/UIAutoationFramework/ui-automation-tests/src/test/resources/jsonobjects/campaignjson/Campaign";
//        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
//        campaign = new GsonBuilder().create().fromJson(bufferedReader, Campaign.class);
        campaign = new Campaign();
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("MyTest1:afterClass");
    }

    @Test
    public void fillCampaignGeneralSettings2() {

        UI.sideBarMenu.navigateToPage("Campaigns");
        UI.campaignsPage.clickCreateCampaignButton();

        UI.newCampaignPage.newCampaignForm.generalSettingsGroup.mainPanel.fillPanel(campaign);


        UI.newCampaignPage.newCampaignForm.generalSettingsGroup.fillMainPanel(campaign);
    }
}
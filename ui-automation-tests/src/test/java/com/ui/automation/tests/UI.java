package com.ui.automation.tests;

import com.ui.automation.elements.sideBar.SideBarMenu;

import com.ui.automation.pages.advertisers.advertisersPage.AdvertisersPage;
import com.ui.automation.pages.advertisers.newAdvertiserPage.NewAdvertiserPage;
import com.ui.automation.pages.campaigns.newCampaginPage.NewCampaignPage;
import com.ui.automation.pages.campaigns.campaignsPage.CampaignsPage;
import com.ui.automation.pages.login.LoginPage;

public class UI extends BasePage {

    public static LoginPage loginPage = new LoginPage();
    public static SideBarMenu sideBarMenu = new SideBarMenu();
    public static CampaignsPage campaignsPage = new CampaignsPage();
    public static NewCampaignPage newCampaignPage = new NewCampaignPage();
    public static AdvertisersPage advertisersPage = new AdvertisersPage();
    public static NewAdvertiserPage neAdvertiserPage = new NewAdvertiserPage();

    @Override
    public void navigateToPage(String pageName) {
        super.navigateToPage(pageName);
    }
}

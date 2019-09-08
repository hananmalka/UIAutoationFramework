package com.ui.automation.tests;

import com.ui.automation.elements.sideBar.SideBarMenu;

public class BasePage {

    public void navigateToPage(String pageName) {
        SideBarMenu sideBarMenu = new SideBarMenu();
        sideBarMenu.navigateToPage(pageName);
    }
}

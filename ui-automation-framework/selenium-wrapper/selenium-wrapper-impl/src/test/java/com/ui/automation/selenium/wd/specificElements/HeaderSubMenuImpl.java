package com.ui.automation.selenium.wd.specificElements;

import com.ui.automation.app.eventBus.EventType;
import com.ui.automation.common.exception.MaasUIAutomationException;
import com.ui.automation.elements.api.Element;
import com.ui.automation.selenium.wd.BaseDriverExecutorImpl;
import com.ui.automation.selenium.wd.DriverServices;
import com.ui.automation.selenium.wd.ExceptionHolder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created with IntelliJ IDEA.
 * User: coheney
 * Date: 01/05/14
 * Time: 16:08
 * To change this template use File | Settings | File Templates.
 */
public class HeaderSubMenuImpl extends BaseDriverExecutorImpl implements HeaderSubMenu {

    public HeaderSubMenuImpl(DriverServices driverservices) {
        super(driverservices);
    }


    @Override
    public void selectSubMenuItem(Element subMenuParent, final String subItemSuffix) {
        EventType et = new EventType(EventType.Message.SPECIFIC_ACTION_SELECT_MENU_ITEM);
        et.setParams(subItemSuffix.replace("-"," "));
        executeAction(subMenuParent, et, new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                String subMenuItemXpath = "//*[@data-aid='sub-menu-item-" + subItemSuffix + "']//a";
                String subMenuItemXpathParent = "//*[@data-aid='sub-menu-item-" + subItemSuffix + "']";
                String ddSubMenuItemDataAid = "//*[@data-aid='dd-sub-menu-item-" + subItemSuffix + "']";


                //find the parent
                logger.info("SUB-MENU-ITEM-LOG: Checking if header sub items exist");
                WebElement mainContainerWebElement = driverServices.getDriver().findElement(by);
                logger.info("SUB-MENU-ITEM-LOG: Checking if header sub items displayed =" +  mainContainerWebElement.isDisplayed());
                if (!mainContainerWebElement.isDisplayed()) {
                    logger.info("SUB-MENU-ITEM-LOG: header sub items Not displayed --> Return False");
                    failedEx.setInner(new MaasUIAutomationException("The parent sub menu element is not visible"));
                    return false;
                }
                logger.info("SUB-MENU-ITEM-LOG: Scrolling to sum menu header");
                driverServices.scrollToView(mainContainerWebElement);

                //on full, subItemsList.isDisplayed() = true
                //on arrows,subItemsList.isDisplayed() = true
                //on dropdown subItemsList.isDisplayed() = false
                logger.info("SUB-MENU-ITEM-LOG: GETTING sub-menu-items-tabs element");
                WebElement subItemsList = mainContainerWebElement.findElement(By.className("sub-menu-items-tabs"));
                logger.info("SUB-MENU-ITEM-LOG: sub-menu-items-tabs element FOUND");
                //on full, subItemsListDropDown.isDisplayed() = false
                //on arrows, subItemsListDropDown.isDisplayed() = false
                //on dropdown, subItemsListDropDown.isDisplayed() = true;
                logger.info("SUB-MENU-ITEM-LOG: GETTING sub-menu-items-dropdown element");
                WebElement subItemsListDropDown = mainContainerWebElement.findElement(By.className("sub-menu-items-dropdown"));
                logger.info("SUB-MENU-ITEM-LOG: sub-menu-items-dropdown FOUND");

                sleep(1000);
                // in case none of the lists displayed

                if (!subItemsList.isDisplayed() && !subItemsListDropDown.isDisplayed()) {
                    logger.info("SUB-MENU-ITEM-LOG: Both sub-menu-items-dropdown AND sub-menu-items-tabs are NOT DISPLAYED --> Returning False");
                    failedEx.setInner(new MaasUIAutomationException("None of the sub-menu list items is displayed!!"));
                    return false;
                } else if (subItemsList.isDisplayed() && subItemsListDropDown.isDisplayed()) {
                    logger.info("SUB-MENU-ITEM-LOG: Both sub-menu-items-dropdown AND sub-menu-items-tabs are DISPLAYED --> Returning False");
                    failedEx.setInner(new MaasUIAutomationException("Both of the sub-menu list items is displayed - (which is not reasonable)!!"));
                    return false;
                } else if (subItemsListDropDown.isDisplayed()) {
                    logger.info("SUB-MENU-ITEM-LOG: Drop down item is displayed, CLICKING it...");
                    subItemsListDropDown.findElement(By.className("dropdown-toggle")).click();
                    logger.info("SUB-MENU-ITEM-LOG: Clicking on: " + ddSubMenuItemDataAid);
                    subItemsListDropDown.findElement(By.xpath(ddSubMenuItemDataAid)).click();

                    return true;
                } else if (subItemsList.isDisplayed()) {
                    logger.info("SUB-MENU-ITEM-LOG: sub-menu-items-tabs is DISPLAYED");
                    boolean goRight = true;
                    while (true) {
                        logger.info("SUB-MENU-ITEM-LOG: CHECKING if  sub item " + subMenuItemXpath + " EXIST?");
                        WebElement desiredWebElement = subItemsList.findElement(By.xpath(subMenuItemXpath));
                        logger.info("SUB-MENU-ITEM-LOG: CHECKING if sub item " + subMenuItemXpath + " DISPLAYED?");
                        if (desiredWebElement.isDisplayed()) {
                            logger.info("SUB-MENU-ITEM-LOG: sub item " + subMenuItemXpath + " DISPLAYED!!!");
                            logger.info("SUB-MENU-ITEM-LOG: Going to CLICK sub item " + subMenuItemXpath );
                            org.openqa.selenium.interactions.Actions builder = new org.openqa.selenium.interactions.Actions(driverServices.getDriver());
                            builder.moveToElement(desiredWebElement).perform();
                            desiredWebElement.click();

                            logger.info("SUB-MENU-ITEM-LOG: sub item " + subMenuItemXpath + " DISPLAYED!!!");
                            logger.info("SUB-MENU-ITEM-LOG: Checking if selected!");
                            WebElement selectedDesiredWebElement = subItemsList.findElement(By.xpath(subMenuItemXpathParent));
                            if(selectedDesiredWebElement.getAttribute("class").contains("selected-subitem")){
                                logger.info("SUB-MENU-ITEM-LOG: Item is selected!!");
                                return true;
                            }
                            else{
                                logger.info("SUB-MENU-ITEM-LOG: Item is NOT selected");
                                failedEx.setInner(new MaasUIAutomationException("Failure to select sub-menu-item!!!"));
                                return false;
                            }


                        } else {
                            logger.info("SUB-MENU-ITEM-LOG: the sub item " + subMenuItemXpath + "NOT DISPLAYED");
                            WebElement goRightWebElement = subItemsList.findElement(By.className("icon-move-right"));
                            logger.info("SUB-MENU-ITEM-LOG: right arrow FOUND!!");
                            WebElement goLeftWebElement = subItemsList.findElement(By.className("icon-move-left"));
                            logger.info("SUB-MENU-ITEM-LOG: left arrow FOUND!!");
                            if (goRight) {
                                if (!goRightWebElement.isEnabled()) {
                                    logger.info("SUB-MENU-ITEM-LOG: right arrow NOT ENABLED!!");
                                    goRight = false;
                                } else {
                                    logger.info("SUB-MENU-ITEM-LOG: CLICKING right arrow !!");
                                    goRightWebElement.click();
                                }
                            }
                            if (!goRight) {
                                if (!goLeftWebElement.isEnabled()) {
                                    logger.info("SUB-MENU-ITEM-LOG: left arrow NOT ENABLED!!");
                                    goRight = true;
                                } else {
                                    logger.info("SUB-MENU-ITEM-LOG: CLICKING left arrow !!");
                                    goLeftWebElement.click();
                                }
                            }
                        }
                    }
                }
                else{
                    logger.info("SUB-MENU-ITEM-LOG: None of the conditions met --> Returning False");
                    failedEx.setInner(new MaasUIAutomationException("None of the sub-menu list items conditions occurs"));
                    return false;
                }

            }
        });
    }
}

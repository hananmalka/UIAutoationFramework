package com.ui.automation.elements.base;

import com.mqm.automation.ui.services.execution.ApplicationContextHolder;
import com.ui.automation.common.element.items.SpecialKeys;
import com.ui.automation.elements.impl.RootElement;
import com.ui.automation.locator.Locator;
import com.ui.automation.elements.api.Element;
import com.ui.automation.selenium.lifecycle.DriverTestContext;
import com.ui.automation.selenium.wd.MaasDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;

/**
 * User: coheney / azariam
 * Date: 09/01/14 / 21/4/16
 */
public class BaseElement implements Element {

	private Locator locator;
	private Element parent;
	private enum elementProperty { VISIBILITY, ENABLEMENT}

	public BaseElement(Locator locator, Element parent) {
		this.locator = locator;
		this.parent = parent;
	}

	public Locator getLocator() {
		return this.locator;
	}

	public Element getParent() {
		return this.parent;
	}

	protected MaasDriver getDriver() {
		// Get this thread's driver
		final DriverTestContext driverTestContext = ApplicationContextHolder.getApplicationContext().getBean(DriverTestContext.class);
		return driverTestContext.getDriver();
	}

	public Element expectVisible() {
		getDriver().expects().elementToBeVisible(this);
		return this;
	}

	public Element expectNotVisible() {
		getDriver().expects().elementNotVisible(this.parent, this);
		return this;
	}

	public void clickOn() {
		expectSpinnerNotVisible();
		getDriver().actions().click(this);
	}

	public void holdCtrlAndClickElement () {
		getDriver().actions().holdKeyAndClick(this);

	}

	public void clickOut() {
		BaseElement element = new BaseTopLevelElement(Locator.xpath("/html/body"));//xpath("/html/body | //*[@data-aid = 'maas-dialog']")
		getDriver().actions().click(element);
	}

	public void doubleClick() {
		expectSpinnerNotVisible();
		getDriver().actions().doubleClick(this);
	}

	public BaseElement getChild(String xPath) {
		String xPathFull = this.getLocator().getExpression() + "//" + xPath;
		BaseElement element = new BaseElement(Locator.xpath(xPathFull), this);
		return element;
	}

	public boolean isThisElementVisible(){
		try {
			this.expectVisible();
		} catch (Exception e){
			return false;
		}
		return true;
	}

	public boolean isThisElementNotVisible(){
		try {
			this.expectNotVisible();
		} catch (Exception e){
			return false;
		}
		return true;
	}

	public boolean isThisChildElementNotVisible(Element notVisibleElement){
		try {
			if (notVisibleElement==null)
			return true;
			getDriver().expects().elementNotVisible(this, notVisibleElement);
		} catch (Exception e){
			return false;
		}
		return true;
	}

	public boolean isThisElementEnabled(){
		try {
			getDriver().expects().elementToBeEnabled(this);
		} catch (Exception e){
			return false;
		}
		return true;
	}

	public boolean isThisElementDisabled(){
		try {
			getDriver().expects().elementToBeDisabled(this);
		} catch (Exception e){
			return false;
		}
		return true;
	}

	public void expectThisElementToBeEnabled(boolean enabled){
		if (enabled) {
			getDriver().expects().elementToBeEnabled(this);
		} else {
			getDriver().expects().elementToBeDisabled(this);
		}
	}

    public boolean waitForElementToAppear(int timeoutSeconds, int seleniumTimeoutSeconds){
        return  waitForElementState(true, elementProperty.VISIBILITY, timeoutSeconds, seleniumTimeoutSeconds);
    }

    public boolean waitForElementToDisappear(int timeoutSeconds, int seleniumTimeoutSeconds) {
        return waitForElementState(false, elementProperty.VISIBILITY, timeoutSeconds, seleniumTimeoutSeconds);
	}

	public boolean waitForElementToBeEnabled(int timeoutSeconds, int seleniumTimeoutSeconds){
		return  waitForElementState(true, elementProperty.ENABLEMENT, timeoutSeconds, seleniumTimeoutSeconds);
	}

	public boolean waitForElementToBeDisabled(int timeoutSeconds, int seleniumTimeoutSeconds) {
		return waitForElementState(false, elementProperty.ENABLEMENT, timeoutSeconds, seleniumTimeoutSeconds);
	}

	private boolean waitForElementState(boolean targetState, elementProperty property, int timeoutSeconds, int seleniumTimeoutSeconds){

		//timing calculations
		int numberOfTries = 0;
		if (timeoutSeconds > 0) { //to avoid division by 0 and allow for 0 number of tries
			numberOfTries = ((timeoutSeconds % seleniumTimeoutSeconds) > 0) ? ((timeoutSeconds / seleniumTimeoutSeconds) + 1) : (timeoutSeconds / seleniumTimeoutSeconds); //rounding up number of tries
		}
		int tryCounter = 1; //starting at 1 so that when timeoutSeconds is  0 there is no sleep period

		boolean currentState;

		//Waiting until TO expires (looping)
		do {

			switch (property){
				case VISIBILITY:
					currentState = this.isThisElementVisible();
					break;
				case ENABLEMENT:
					currentState = this.isThisElementEnabled();
					break;
				default:
					currentState = false;
					break;
			}

			//Waiting with sleep only if node is visible/enabled as selenium FW waits it's full timeout when it's not;
			//Therefore when waiting to disappear, selenium does not wait it's timeout as it finds the element, we need to manually wait the actual timeout period.
			//if we are waiting for the element to disappear/not be enabled and we did not yet reach our target state then we have to execute sleep
			if (!targetState && (currentState != targetState)) {
				sleepFor(seleniumTimeoutSeconds);
			}

			tryCounter++;
		} while ((currentState != targetState) && (tryCounter <= numberOfTries));

		return currentState;

	}

	public static void sleepFor(int seleniumTimeoutSeconds) {
		try {
            sleep((seleniumTimeoutSeconds * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}

	public BaseElement sendEnterKeyToElement() {
        getDriver().actions().sendSpecialKeys(this, SpecialKeys.ENTER);
        return this;
    }

	public BaseElement sendCtrlCToElement() {
		getDriver().actions().sendSpecialKeys(this, SpecialKeys.chord(SpecialKeys.CONTROL + "c"));
		return this;
	}

	public BaseElement sendCtrlVToElement() {
		getDriver().actions().sendSpecialKeys(this, SpecialKeys.chord(SpecialKeys.CONTROL + "v"));
		return this;
	}

	public String getText(){
		expectSpinnerNotVisible();
		return getDriver().finds().getText(this);
	}

    public String getTagName(){
        return getDriver().finds().getTagName(this);
    }

	public void setValue (String valueToEnter) {
		expectSpinnerNotVisible();
		isThisElementVisible();
		clearFieldValue();
		getDriver().actions().sendSpecialKeys(this, valueToEnter);
	}

    public void sendStringAsKeysToElement (String keysToSend) {
        getDriver().actions().sendSpecialKeys(this, keysToSend);
    }

    public void sendSpecialKeysToElement (CharSequence... specialKeysToSend) {
        getDriver().actions().sendSpecialKeys(this, specialKeysToSend);
    }

    public void sendBackspaceNTimesToElement(int times){
		expectSpinnerNotVisible();
		getDriver().actions().clickBackspaceNTimes(this, times);
	}

	public void clearValueWithBackspace(){
		sendBackspaceNTimesToElement(this.getText().length());
	}

	public BaseElement clearFieldValue () {
		expectSpinnerNotVisible();
		getDriver().expects().elementToBeEnabled(this);
		getDriver().actions().clearText(this);
		return this;
	}

	public boolean numberOfElements(int numberOfElements) {
		try {
			getDriver().expects().numberOfElementsToBe(this,numberOfElements);
		} catch (Exception e){
			return false;
		}
		return true;
	}

	public void dragThisElementTo(Element target){
        getDriver().actions().dragElementTo(this, target);
    }

	public void dragThisElementTo(int xRelativeToElement, int yRelativeToElement){
		getDriver().actions().dragElementToOffset(this, xRelativeToElement, yRelativeToElement);
	}

	public void expectTooltipToBe(String expectedTooltip){
		getDriver().expects().elementToContainAttrValue(this, "title", expectedTooltip);
	}

	public int getElementIndex(){
		return getDriver().finds().index(this);
	}

	public void hoverOverElement(){
		getDriver().actions().hoverOver(this);
	}

	public void expectElementToContainAttributeValue(String attribute, String value){
		getDriver().expects().elementToContainAttrValue(this, attribute, value);
	}

	/**
	 * Useful methods when the element exists in the DOM but it's not visible (BaseElement#isThisElementVisible returns false)
	 */
	public void expectExists() {
		getDriver().expects().lowLevel().elementExistsInTheDom(this);
	}

	public void expectNotExists() {
		getDriver().expects().lowLevel().elementNotExistsInTheDom(this.getParent(), this);
	}

	public boolean exists() {
		try {
			this.expectExists();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private void expectSpinnerNotVisible() {
		BaseElement loadingSpinner = new BaseTopLevelElement(Locator.className("loading-spinner"));
		getDriver().expects().elementNotVisible(RootElement.getInstance(), loadingSpinner);
	}

}

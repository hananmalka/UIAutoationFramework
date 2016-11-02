package com.ui.automation.selenium.wd;

import com.ui.automation.app.eventBus.EventType;
import com.ui.automation.common.exception.MaasUIAutomationException;
import com.ui.automation.elements.api.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

/**
 * Created by katzirn on 18/12/2014.
 */
public class LowLevelImpl extends BaseDriverExecutorImpl implements LowLevel {
	protected LowLevelImpl(DriverServices driverservices) {
		super(driverservices);
	}

	@Override
	public void elementExistsInTheDom(Element element) {
		executeAction(element, new EventType(EventType.Message.EXPECT_EXISTS), new ActionExecutor() {
			@Override
			public boolean execute(By by, ExceptionHolder failedEx) {
				return atomicElementExistsInTheDom(by);
			}
		});
	}

	@Override
	public void elementNotExistsInTheDom(final Element existElement, Element notExistElement) {
		String xpathVisElement = driverServices.getXpath(existElement);
		EventType et = new EventType(EventType.Message.EXPECT_NOT_EXISTS);
		et.setParams(xpathVisElement);
		executeAction(notExistElement, et, new ActionExecutor() {
			@Override
			public boolean execute(By by, ExceptionHolder failedEx) {
				return atomicElementNotExistsInTheDom(by, existElement);

			}
		});
	}

	private boolean atomicElementExistsInTheDom(By by) {
		try {
			driverServices.getDriver().findElement(by);
			return true;
		} catch (NoSuchElementException ex) {
			return false;
		}
	}

	private boolean atomicElementNotExistsInTheDom(By by, Element existElement) {
		final By byExist = driverServices.getBy(existElement);
		// Wait for the existing element to... exist :)
		driverServices.getDriver().findElement(byExist);
		WebElement existWebElement;
		try {
			existWebElement = driverServices.getDriver().findElement(by);
		} catch (NoSuchElementException ex) {
			return true;
		}

		throw new MaasUIAutomationException("Element is still in the DOM " + by);
	}
}

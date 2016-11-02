package com.ui.automation.selenium.wd.specificElements;

import com.ui.automation.app.eventBus.EventType;
import com.ui.automation.elements.api.Element;
import com.ui.automation.common.element.items.SpecialKeys;
import com.ui.automation.selenium.wd.BaseDriverExecutorImpl;
import com.ui.automation.selenium.wd.DriverServices;
import com.ui.automation.selenium.wd.ExceptionHolder;
import com.thoughtworks.selenium.webdriven.JavascriptLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by coheney on 17/12/2014.
 */
public class DslCodeMirrorEditorImpl extends BaseDriverExecutorImpl implements DslCodeMirrorEditor {

    private JavascriptLibrary jsLib = new JavascriptLibrary();
    private final Element dslEditorElement;
    //  private final By dslEditorBy;


    /**
     * @param driverservices
     * @param dslEditorElement  - the element locator must be: Locator.className("dsl-expression-hint-editor")
     */
    public DslCodeMirrorEditorImpl(DriverServices driverservices, Element dslEditorElement) {
        super(driverservices);
        this.dslEditorElement = dslEditorElement;


    }

    @Override
    public void setValue(final String value) {
        EventType et = new EventType(EventType.Message.ACTION_SEND_TEXT);
        et.setParams(value, "{NONE}");
        executeAction(dslEditorElement, et, new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                WebElement mainElement = driverServices.getDriver().findElement(by);
                WebElement codeMirror = mainElement.findElement(By.className("CodeMirror"));

                driverServices.executeJavaScript("return arguments[0].CodeMirror.setValue(\"" + value + "\");", codeMirror);

                //out of focus
                mainElement.click();
                sleep(150);
                try {
                    mainElement.sendKeys(SpecialKeys.TAB);
                } catch (Exception ex) {
                    //TODO: check how to remove focus with out this exception
                }

                return true;
            }
        });
    }

}

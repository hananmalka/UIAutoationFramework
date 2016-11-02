package com.ui.automation.selenium.wd.specificElements;


import com.ui.automation.app.eventBus.EventType;
import com.ui.automation.elements.api.Element;
import com.ui.automation.selenium.wd.ActionsImpl;
import com.ui.automation.selenium.wd.BaseDriverExecutorImpl;
import com.ui.automation.selenium.wd.DriverServices;
import com.ui.automation.selenium.wd.ExceptionHolder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by peere on 28/05/2014.
 */
public class CKEditorImpl extends BaseDriverExecutorImpl implements CKEditor {

    private final ActionsImpl actions;

    public CKEditorImpl(DriverServices driverservices) {
        super(driverservices);
        actions = new ActionsImpl(driverServices);
    }

    @Override
    public void updateModel(Element inputRichText) {
        By driverServicesBy = driverServices.getBy(inputRichText);
        final WebElement richTextElement = driverServices.getDriver().findElement(driverServicesBy);
        final WebElement editorContent = richTextElement.findElement(By.className("pl-text-editor-content"));
        String name = editorContent.getAttribute("name");
        if (name == null) {
            name = "";
        }
        ((JavascriptExecutor) driverServices.getDriver()).executeScript
                ("angular.element(document.body).injector().get('$rootScope').$broadcast('pl-text-editor-blur-" + name + "');");
    }

    @Override
    public String getUploadImageUrl() {
        // Get the input element under the first occurrence of an HTML element with class "cke_dialog_ui_text"
        WebElement urlInput = getImageInfoUrlInput();
        waitForText(urlInput);
        return actions.getText(urlInput);
    }

    private void waitForText(final WebElement element) {
        driverServices.getFluentWait().until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return actions.getText(element).length() > 0;
            }
        });
    }

    @Override
    public void setUploadImageUrl(String path) {
        WebElement urlInput = getImageInfoUrlInput();
        actions.atomicSendText(urlInput, getImageInfoUrlBy(), path, path);
    }

    @Override
    public void appendText(Element richTextEditor, final CharSequence... charSequence) {
        EventType eventType = new EventType(EventType.Message.ACTION_SEND_TEXT);
        eventType.setParams(charSequence.toString(), "{NONE}");
        executeAction(richTextEditor, eventType, new ActionExecutor() {
                    @Override
                    public boolean execute(By by, ExceptionHolder failedEx) {
                        WebElement editor = driverServices.getDriver().findElement(by);
                        editor.sendKeys(charSequence);
                        return true;
                    }
                });
    }

    @Override
    public void selectColor(Element button, String colorHex) {
        By byButton = driverServices.getBy(button);
        WebElement buttonWe = driverServices.getDriver().findElement(byButton);
        //using js since the driver click not working properly for this picker button
        driverServices.executeJavaScript("arguments[0].click()", buttonWe);

        WebDriverWait wait = new WebDriverWait(driverServices.getDriver(), 10000);
   /*     try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        By classBy = new By.ByClassName("cke_panel_frame");
        WebDriver iframe = wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(classBy));

        // System.out.println(((JavascriptExecutor) iframe).executeScript("return arguments[0].outerHTML", iframe.findElement(By.tagName("body"))));
        // System.out.println(((JavascriptExecutor) driverServices.getDriver()).executeScript("return arguments[0].outerHTML", driverServices.getDriver().findElement(By.tagName("body"))));
        WebDriverWait iframeWait = new WebDriverWait(iframe, 10000);
        WebElement colorPanel = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@class,'cke_panel_block') and not(contains(@style,'display: none'))]")));


        WebElement colorBox = colorHex != null ?
                colorPanel.findElement(By.xpath(".//*[@class='cke_colorbox' and contains(@onclick,'" + colorHex + "')]")) :
                colorPanel.findElement(By.xpath(".//*[@class='cke_colorauto']"));
        colorBox.click();
        driverServices.getDriver().switchTo().defaultContent();

    }

    @Override
    public void moveCursorToEnd(Element inputRichText) {

        String id = getEditorId(inputRichText);
        String script = "var editor = CKEDITOR.instances." + id + "; var r = editor.ui.editor.createRange(); r.moveToElementEditEnd(r.root); " +
                "var sele = editor.ui.editor.getSelection(); sele.selectRanges([r]);";
        driverServices.executeJavaScript(script);
    }

    @Override
    public String getEditorId(Element inputRichText){
        By driverServicesBy = driverServices.getBy(inputRichText);
        final WebElement richTextElement = driverServices.getDriver().findElement(driverServicesBy);
        final WebElement editorContent = richTextElement.findElement(By.className("pl-text-editor-content"));
        return editorContent.getAttribute("id");
    }

    @Override
    public String getUploadImageInputId(Element inputRichText) {
        String richBoxid = this.getEditorId(inputRichText);

        String script = "return CKEDITOR.instances." + richBoxid + ".filePickerElement[0].id;";
        return (String) driverServices.executeJavaScript(script);

    }

    /**
     * Returns the WebElement denoting the HTML input element that accepts the image url to embed in the editor
     *
     * @return
     */
    private WebElement getImageInfoUrlInput() {
        return driverServices.getDriver().findElements(getImageInfoUrlBy()).get(0);
    }

    private By getImageInfoUrlBy() {
        return By.xpath("//div[contains(@class,'cke_editor_') and not(contains(@style, 'none'))]//*[@class='cke_dialog_ui_text'][1]//input");
    }
}

package com.ui.automation.elements.dialogs;

import com.ui.automation.elements.base.BaseTopLevelElement;
import com.ui.automation.locator.Locator;

/**
 * Created by azariam on 14/06/2016.
 */
public class FileUpload extends BaseTopLevelElement {

    public FileUpload(Locator locator) {
        super(locator);
    }

    public void uploadFile(String fullFilePath){
        getDriver().actions().sendSpecialKeys(this, fullFilePath);
    }
}

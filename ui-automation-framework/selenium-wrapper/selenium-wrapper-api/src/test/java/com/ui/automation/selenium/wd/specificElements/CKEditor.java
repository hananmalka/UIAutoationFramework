package com.ui.automation.selenium.wd.specificElements;

import com.ui.automation.elements.api.Element;

/**
 * Created by peere on 28/05/2014.
 */
public interface CKEditor {

    /**
     * This method forces an update to the SPMaaS Angular model underlying the editor,
     * i.e. copying values from the editor object to the Angular model
     */
    void updateModel(Element inputRichText);

    /**
     * Returns the URL text value as presented in the "Upload image" dialog
     *
     * @return
     */
    String getUploadImageUrl();

    /**
     * Sets the URL text value as presented in the "Upload image" dialog
     */
    void setUploadImageUrl(String path);


    void appendText(Element richTextEditor, CharSequence... charSequence);

    void selectColor(Element button,String colorHex);


    void moveCursorToEnd(Element inputRichText);

    String getEditorId(Element inputRichText);

    /**
     * get the related input element of the given rich text box.
     * Since the input element located on the body with dynamic id we must get this id from the rich text by JS
     * @param inputRichText
     * @return
     */
    String getUploadImageInputId(Element inputRichText);
}

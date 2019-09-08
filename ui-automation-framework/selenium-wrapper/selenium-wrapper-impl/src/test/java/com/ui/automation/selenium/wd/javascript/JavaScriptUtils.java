package com.ui.automation.selenium.wd.javascript;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;

/**
 * Created by azariam on 24/05/2016.
 */
public class JavaScriptUtils {

    public String getJavaScriptDNDToOffsetScript() {
        return getJavaScriptScript("drag_and_drop_to_offset.js");
    }

    public String getJavaScriptDNDToTargetElementScript() {
        return getJavaScriptScript("drag_and_drop_to_element.js");
    }

    private String getJavaScriptScript(String fileName){
        try {
            URL url = Resources.getResource(this.getClass().getPackage().getName() + "/" + fileName);
            return Resources.toString(url, Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}

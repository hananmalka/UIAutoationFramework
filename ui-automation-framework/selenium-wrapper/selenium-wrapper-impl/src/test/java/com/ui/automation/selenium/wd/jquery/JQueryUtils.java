package com.ui.automation.selenium.wd.jquery;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by azariam on 19/05/2016.
 */
public class JQueryUtils {

    public void defineJQueryForDriver(WebDriver driver){

        try {

            URL url = Resources.getResource(this.getClass().getPackage().getName() + "/insert_jquery_to_page.js");
            String jQueryLoader = Resources.toString(url, Charsets.UTF_8);

            // give jQuery time to load asynchronously
            driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeAsyncScript(jQueryLoader /*, http://localhost:8080/jquery-1.7.2.js */);

            // ready to rock
            js.executeScript(
                    "jQuery(function($) { " +
                            " $('input[name=\"q\"]').val('bada-bing').closest('form').submit(); " +
                            " }); "
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getJQueryDNDScript() {
        try {
            URL url = Resources.getResource(this.getClass().getPackage().getName() + "/drag_and_drop_helper.js");
            return Resources.toString(url, Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

}

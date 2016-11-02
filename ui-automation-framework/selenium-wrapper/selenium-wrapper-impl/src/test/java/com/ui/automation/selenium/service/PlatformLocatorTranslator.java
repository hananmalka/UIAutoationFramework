package com.ui.automation.selenium.service;

import com.ui.automation.elements.impl.BaseElementTranslator;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: coheney
 * Date: 18/03/14
 * Time: 12:15
 * To change this template use File | Settings | File Templates.
 */
@Component
public class PlatformLocatorTranslator extends BaseElementTranslator {

    protected PlatformLocatorTranslator() {
        super(getFilePath());
    }

    private static InputStream getFilePath(){
        return PlatformLocatorTranslator.class.getClassLoader().getResourceAsStream("locatorMap.xml");
    }

}

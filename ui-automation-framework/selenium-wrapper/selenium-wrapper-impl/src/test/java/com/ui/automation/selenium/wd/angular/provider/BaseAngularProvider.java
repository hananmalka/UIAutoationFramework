package com.ui.automation.selenium.wd.angular.provider;

import com.ui.automation.common.exception.MaasUIAutomationException;
import com.ui.automation.selenium.wd.angular.AngularModuleProvider;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Base class for injecting Angular modules before resumeBootstrap is called.
 *
 * This class reads the content of a .js file which will be injected as a new module
 * before resumeBootstrap is called.
 *
 * The class assumes that the js file name is identical to the module name provided to the constructor
 * with .js suffix, e.g. if the constructor is called with "myModule" then it reads the file
 * com/hp/maas/platform/ui/test/selenium/wd/angular/myModule.js which should exist in the JVM classpath
 */
public abstract class BaseAngularProvider implements AngularModuleProvider {

    private static final String RELATIVE_PATH_TO_JS_SOURCES = "com/hp/ui/automation/selenium/wd/angular";

    private final String moduleName;
    private final String fileName;

    protected BaseAngularProvider(String moduleName) {
        this.moduleName = moduleName;
        this.fileName = moduleName + ".js";
    }

    @Override
    public String getModuleName() {
        return moduleName;
    }

    /**
     * Returns parameters to be replaced in the module definition except for the module name
     * @return
     */
    protected abstract List<String> getModuleDefinitionParameters();

    @Override
    public String getModuleDefinition() {
        String result = "";
        try (InputStream in = this.getClass().getResourceAsStream("/" + RELATIVE_PATH_TO_JS_SOURCES + "/" + fileName)) {
            byte[] buffer = new byte[in.available()];
            in.read(buffer);
            String decoded = new String(buffer, "UTF-8");

            // Using copy-constructor since the backed list can be immutable
            List<String> moduleDefinitionParameters = new ArrayList(getModuleDefinitionParameters());
            // Assumes that the module name is not hard coded in the JS file, but represented by %s as its first param to replace
            moduleDefinitionParameters.add(0, moduleName);
            result = String.format(decoded, moduleDefinitionParameters.toArray());

        } catch (IOException e) {
            throw new MaasUIAutomationException("Failed to read file " + fileName, e);
        }
        return result;
    }
}

package com.ui.automation.selenium.wd.angular.provider;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * Created by peere on 08/04/2014.
 */
@Component
public class AngularExceptionServiceDecoratorProvider extends BaseAngularProvider {

    private static final String MODULE_NAME = "exceptionHandlerDecorator";

    protected AngularExceptionServiceDecoratorProvider() {
        super(MODULE_NAME);
    }

    @Override
    protected List<String> getModuleDefinitionParameters() {
        return Collections.emptyList();
    }
}

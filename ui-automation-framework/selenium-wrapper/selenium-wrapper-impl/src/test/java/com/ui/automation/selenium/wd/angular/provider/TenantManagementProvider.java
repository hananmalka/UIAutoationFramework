package com.ui.automation.selenium.wd.angular.provider;

import com.ui.automation.common.junit.TestDetailsHolder;
import com.ui.automation.selenium.wd.angular.AngularModuleProvider;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by peere on 08/04/2014.
 */
// TODO: DANA - disabled provider since this is an applicative MAAS provider that should be removed
//@Component
public class TenantManagementProvider implements AngularModuleProvider {

    private static final String MODULE_NAME = "tenantManagementDecorator";

    @Autowired
    private TestDetailsHolder testDetailsHolder;

    @Override
    public String getModuleName() {
        return MODULE_NAME;
    }

    @Override
    public String getModuleDefinition() {


        String originalIsTrialTenant = "return originalIsTrialTenant();\n";
        String originalIsTrial = "return originalIsTrial();\n";
        String mockIsTrialTenant = "var deferred = $q.defer(); deferred.resolve(true); return deferred.promise;\n";
        String mockIsTrial = "return true;\n";
        String isTrialTenantStr = testDetailsHolder.getTestDetails().isTrialTenant() ? mockIsTrialTenant : originalIsTrialTenant;
        String isTrialStr = testDetailsHolder.getTestDetails().isTrialTenant() ? mockIsTrial : originalIsTrial;
        return "angular.module('" + MODULE_NAME + "', ['server-communication-services']).config(['$provide', function ($provide) {\n"
                + "    $provide.decorator('TenantManagement', function ($delegate, $q) {\n"
                + "         var originalIsTrialTenant = $delegate.isTrialTenant,\n"
                + "             originalIsTrial = $delegate.isTrial,\n"
                + "             isTrialTenantMock = function () {\n"
                + isTrialTenantStr
                + "             };\n"
                + "         $delegate.isTrialTenant = isTrialTenantMock;\n"
                + "         $delegate.isTrial = function() {\n"
                + isTrialStr
                + "         };\n"
                + "         return $delegate;\n"
                + "    });\n"
                + "}]);\n";
    }
}

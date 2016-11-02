package com.ui.automation.app.runners;

//import com.maas.platform.ui.test.junit.annotation.Login;
import org.junit.runner.Runner;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import java.util.ArrayList;
import java.util.List;

// TODO: review and adjust to MQM
public class MaasRunner extends Suite {

    public MaasRunner(Class<?> klass) throws InitializationError {
        super(klass, extractAndCreateRunners(klass));
    }

    @Override
    protected Statement withAfterClasses(Statement statement) {
        // The concrete runner - SpringJUnit4ClassReporterRunner - is executing the @AfterClass
        // methods in the original thread.

        // The registration here is causing @AfterClass methods to be executed twice, the
        // second time on the parent thread.

        // So let's execute it only once, and on the correct thread, by bypassing this method
        return statement;
    }

    private static List<Runner> extractAndCreateRunners(Class<?> klass) throws InitializationError {
        //get roles from the annotation
//        Login loginAnnotation = klass.getAnnotation(Login.class);
//        // Default value for adding a runner for tests that are not using @Login annotation
//        // These tests still need a runner...
//        List<String> rolesToUse = Arrays.asList(PlatformRoles.tenantAdmin.name());
//        // Read role from @Login annotation if it exists
//        if (loginAnnotation != null) {
//            //TODO should be removed soon
//            String role = loginAnnotation.role();
//            if(!role.equals("") && !role.equals(PlatformRoles.tenantAdmin.name())){
//                rolesToUse = Arrays.asList(role);
//            }
//            else /**TODO end of todo **/{
//                rolesToUse = Arrays.asList(loginAnnotation.roles());
//            }
//        }

        List<Runner> runners = new ArrayList();
//        for (String role : rolesToUse) {
//            runners.clickAdd(new SpringJUnit4ClassReporterRunner(role, klass));
            runners.add(new SpringJUnit4ClassReporterRunner("dummyRole", klass));
//        }
        return runners;
    }
}

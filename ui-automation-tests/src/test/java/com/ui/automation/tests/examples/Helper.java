package com.ui.automation.tests.examples;

import com.ui.automation.elements.api.Element;
import com.ui.automation.selenium.lifecycle.DriverTestContext;
import com.ui.automation.selenium.wd.DriverServices;
import com.ui.automation.elements.entitytree.EntityTreeNode;
import com.ui.automation.elements.entities.appmodule.AppModuleElements;
import com.ui.automation.elements.entities.appmodule.AppModuleTree;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Created by Dana Shalev on 25/11/2015.
 */
@ContextConfiguration(locations = {"classpath:/META-INF/spring/ui-automation-tests-context.xml"})
public class Helper extends AbstractJUnit4SpringContextTests {

    @Autowired
    AppModuleElements appModule;

    @Autowired
    DriverTestContext driverTestContext;

    DriverServices driverServices;

    AppModuleTree tree;

    EntityTreeNode root;

    @Before
    public void init() {
        driverServices = new DriverServices(100);
        tree = appModule.tree;
        root = tree.root();
    }


//    @TestElements
    public void testPath() {
        String[] path = {"p1", "p2"};
        EntityTreeNode p2 = tree.root().descendant(path);
        printXPath(p2);
    }

    @Test
    public void test2() {
        printXPath(tree.toolbar().button("clickAdd"));
    }

    private void printXPath(Element element) {
        System.out.println(driverServices.getXpath(element));
    }
}

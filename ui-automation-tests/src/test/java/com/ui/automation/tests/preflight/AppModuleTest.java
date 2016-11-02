package com.ui.automation.tests.preflight;

import com.ui.automation.elements.RootElement;
import com.ui.automation.elements.controls.fatlines.FatLines;
import com.ui.automation.elements.controls.grid.Grid;
import com.ui.automation.elements.entities.appmodule.AppModuleForm;
import com.ui.automation.elements.entities.test.NewManualTestDialog;
import com.ui.automation.elements.entities.test.TestsToolbar;
import com.ui.automation.reporter.api.TestDoc;
import com.ui.automation.selenium.wd.MaasDriver;
import com.ui.automation.elements.entities.appmodule.NewAppModuleDialog;
import com.ui.automation.elements.entities.appmodule.AppModulesToolbar;
import com.ui.automation.elements.entitytree.EntityTreeNode;
import com.ui.automation.tests.BaseAutomationTest;
import com.ui.automation.tests.NonStaticBeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Dana Shalev on 24/11/2015.
 */
public class AppModuleTest extends BaseAutomationTest {

    @Autowired
    MaasDriver driver;

    static private EntityTreeNode treeRoot;
    static private AppModulesToolbar treeToolbar;
    static private AppModuleForm appModuleForm;
    static private TestsToolbar testsToolbar;
    static private Grid testGrid;
    static private FatLines testFatLines;

    @NonStaticBeforeClass
    public void init() {
        treeRoot = RootElement.appModuleElements.tree.root();
        treeToolbar = RootElement.appModuleElements.tree.toolbar();
        appModuleForm = RootElement.appModuleElements.form;
        testsToolbar = appModuleForm.testsContainer().toolbar();
        testGrid = appModuleForm.testsContainer().grid();
        testFatLines = appModuleForm.testsContainer().fatLines();
    }

    @NonStaticBeforeClass
    public void navigateToModule() {
        //RootElement.header.navigationMenu().navigateToAppModules();
    }

    // TODO: create generic method for adding entities

    // adds multiple product areas and returns the node for the last added item
    private EntityTreeNode addAppModules(EntityTreeNode parent, String[] names) {
        int lastIndex = names.length - 1;

        parent.select();

        treeToolbar.clickEnterEdit().clickAdd();

        NewAppModuleDialog dlg = RootElement.appModuleElements.newDialog;

        for (int i = 0; i < names.length; i++) {
            dlg.setName(names[i]);
            dlg.clickAdd(i == lastIndex);
        }
        treeToolbar.clickExitEdit();

        return parent.child(names[lastIndex]);
    }

    private EntityTreeNode addAppModule(EntityTreeNode parent, String name) {
        return addAppModules(parent, new String[]{name});
    }

    private void addTests(String[] names) {
        int lastIndex = names.length - 1;

        testsToolbar.clickAdd();

        NewManualTestDialog dlg = RootElement.testElements.newDialog();

        for (int i = 0; i < names.length; i++) {
            dlg.setName(names[i]);
            dlg.clickAdd(i == lastIndex);
        }
    }

    private void addTest(String name) {
        addTests(new String[]{name});
    }

    @Test
    @TestDoc(name = "Create application module")
    public void create() {
        EntityTreeNode node = addAppModule(treeRoot, "create-app");
        node.expectVisible();
    }

    @Test
    @TestDoc(name = "Select application module")
    public void select() {
        EntityTreeNode node = addAppModule(treeRoot, "select-app");
        node.select();
        appModuleForm.header().expectValueToBe("select-app");
    }

    @Test
    @TestDoc(name = "expand/collapse application modules in the tree")
    public void expandCollapse() {
        EntityTreeNode node1 = addAppModule(treeRoot, "expandCollapse1");
        EntityTreeNode node2 = addAppModule(node1, "expandCollapse2");

        node1.collapse();
        node2.expectNotVisible();
        node1.expand();
        node2.expectVisible();
    }

    @Test
    @TestDoc(name = "create tests in the grid view and fat lines view")
    public void createTests() {
        reporter.testStep("create 2 application modules - parent/child");
        EntityTreeNode parent = addAppModule(treeRoot, "tests-app1");
        EntityTreeNode child = addAppModule(parent, "tests-app2");

        reporter.testStep("select the child module and go to tests tab");
        child.select();
        appModuleForm.testsTab().select();

        reporter.testStep("switch to fat lines view");
        TestsToolbar testsToolbar = appModuleForm.testsContainer().toolbar();
        testsToolbar.clickFatLinesView();

        reporter.testStep("add test");
        addTest("Test1");
        testFatLines.getLineByName("Test1").expectVisible();

        reporter.testStep("switch to grid view");
        testsToolbar.clickGridView();
        testGrid.expectCellWithText("Test1");

        reporter.testStep("add test");
        addTest("Test2");
        testGrid.expectCellWithText("Test2");

        reporter.testStep("switch to fat lines view");
        testsToolbar.clickFatLinesView();
        testFatLines.getLineByName("Test2").expectVisible();

        reporter.testStep("select the child module and go to tests tab");
        parent.select();
        testsToolbar.clickGridView();

        testGrid.expectNumberOfRows(0);

        reporter.testStep("click `include children`");
        testsToolbar.clickIncludeChildren();
        testGrid.expectNumberOfRows(2);
    }
}
